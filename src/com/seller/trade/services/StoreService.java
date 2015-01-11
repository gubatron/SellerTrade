/**
 * The MIT License
 ===============

 Copyright (C) 2015 SellerTrade Developers

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject
 to the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.seller.trade.services;

import com.frostwire.util.HttpClient;
import com.frostwire.util.JdkHttpClient;
import com.seller.trade.core.Configuration;
import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.models.Product;
import com.seller.trade.models.SearchResult;
import com.seller.trade.models.SearchResultList;
import com.seller.trade.services.dht.DHTNode;
import com.seller.trade.services.dht.DHTService;
import com.seller.trade.utils.JsonUtils;

import java.util.*;

/**
 * Created by gubatron on 1/10/15.
 */
public class StoreService {
    private final Configuration configuration;
    private final ServiceBroker broker;

    private final List<Product> products;

    public StoreService(Configuration configuration, ServiceBroker broker) {
        this.configuration = configuration;
        this.broker = broker;
        products = new ArrayList<Product>();
    }

    //todo: put this on ExecutorService and do it in the background.
    public void announceProducts() {
        final DHTService dhtService = broker.getDhtService();
        final int httpPort = configuration.getInt(ConfigurationKeys.ST_SERVER_PORT);
        loadProducts();

        for (Product p : products) {
            dhtService.announceKeywords(Arrays.asList(p.keywords), httpPort);
        }
    }

    /**
     * In the future we'll have here a parametric interface to load products from
     * whatever e-commerce solution you're already using.
     * <p>
     * For now, hackathon purposes, we'll just load a hardcoded list.
     * TODO-HACKATHON: Load product list from JSON text file.
     */
    private void loadProducts() {
        Product p = new Product();
        p.id = 1;
        p.name = "Zip Hoodie 2.0 (Unisex)";
        p.description = "This is a perfect light weight (7.5oz) Heather Charcoal Hoodie for all occasions.  Offers a fitted body with contrast french terry interior and contrast natural-colored exposed zipper tape. \n" +
                "\n" +
                "It's 60% cotton 40% polyester blend french terry, which makes it very soft, yet durable.\n" +
                "\n" +
                "The hoodie has a music player/smartphone holder inside right pocket and an inner pocket hole to pass headphones cables inside the hoodie.\n" +
                "\n" +
                "Thumbholes at cuffs, metal #5 zipper and twill neck tape in a soft off-white color add extra personality and style.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers!";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/45/images/249/new_hoodie_front__18798.1405366030.300.400.png?c=2";
        p.usdPrice = 32.99f;

        //these are the keywords we'll use to announce this product on the DHT.
        p.keywords = new String[]{"hoodie", "frostwire", "sweater", "unisex", "zip hoodie"};
        products.add(p);

        p = new Product();
        p.id = 2;
        p.name = "T-Shirt (Male, Different Colors)";
        p.description = "Made in the USA, 100% cotton. FrostWire male t-shirt in blue, yellow and charcoal.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers.";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/36/images/208/blue__76605.1405366443.300.400.png?c=2";
        p.usdPrice = 19.99f;
        p.keywords = new String[]{"tshirt", "frostwire tshirt", "shirt", "male tshirt"};

        products.add(p);
    }

    public List<SearchResult> query(String q, int hops) {
        List<SearchResult> results = new LinkedList<SearchResult>();

        List<SearchResult> local = queryLocal(q);
        results.addAll(local);

        if (hops > 0) {
            List<SearchResult> remote = queryNodes(q, hops - 1);
            results.addAll(remote);
        }

        return results;
    }

    private List<SearchResult> queryNodes(String q, int hops) {
        List<SearchResult> results = new LinkedList<SearchResult>();

        List<DHTNode> nodes = broker.getDhtService().getNodes(q);

        for (DHTNode n : nodes) {
            if (!broker.getServerIp().equals(n.getIPAddress())) {
                results.addAll(queryNode(n, q, hops));
            }
        }

        return results;
    }

    private List<SearchResult> queryNode(DHTNode node, String q, int hops) {
        HttpClient httpClient = new JdkHttpClient();

        String url = "http://" + node.getIPAddress() + ":" + node.getHttpPort() + "/search/?q=" + q + "&hops=" + hops;
        System.out.println(url);
        try {
            String s = httpClient.get(url);
            SearchResultList searchResultList = JsonUtils.toObject(s, SearchResultList.class);
            if (searchResultList.results != null) {
                return searchResultList.results;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private List<SearchResult> queryLocal(String q) {
        List<SearchResult> results = new LinkedList<SearchResult>();

        for (Product p : products) {
            if (matchProduct(p, q)) {
                SearchResult sr = new SearchResult();
                sr.product = p;
                //sr.store =
                results.add(sr);
            }
        }

        return results;
    }

    private boolean matchProduct(Product p, String q) {
        for (String keyword : p.keywords) {
            if (q.equals(keyword)) {
                return true;
            }
        }

        return false;
    }

    public Product getProduct(long id) {
        for (Product p : products) {
            if (p.id == id) {
                return p;
            }
        }

        return null;
    }
}
