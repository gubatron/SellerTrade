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
import com.seller.trade.models.Store;
import com.seller.trade.services.dht.DHTNode;
import com.seller.trade.services.dht.DHTService;
import com.seller.trade.utils.JsonUtils;

import java.util.*;

public final class StoreService {

    private final Configuration configuration;
    private final DHTService dhtService;
    private final String serverAddress;
    private final int serverPort;
    private final String storeName;
    private final List<Product> products;

    public StoreService(Configuration configuration, DHTService dhtService) {
        this.configuration = configuration;
        this.dhtService = dhtService;
        serverAddress = configuration.getString(ConfigurationKeys.ST_SERVER_IP);
        serverPort = configuration.getInt(ConfigurationKeys.ST_SERVER_PORT);
        storeName = configuration.getString(ConfigurationKeys.ST_SITE_NAME);

        int productsSet = configuration.getInt(ConfigurationKeys.ST_PRODUCTS_SET);
        if (productsSet == 1) {
            products = ProductsData.set1();
        } else if (productsSet == 2) {
            products = ProductsData.set2();
        } else {
            products = new ArrayList<Product>();
        }
    }

    public void announceProducts() {
        final int httpPort = configuration.getInt(ConfigurationKeys.ST_SERVER_PORT);

        for (Product p : products) {
            dhtService.announceKeywords(Arrays.asList(p.keywords), httpPort);
        }
    }

    public List<SearchResult> query(String q, int hops) {
        List<SearchResult> results = new LinkedList<SearchResult>();

        List<SearchResult> local = queryLocal(q);
        results.addAll(local);

        if (hops > 0 && !"all".equals(q)) {
            List<SearchResult> remote = queryNodes(q, hops - 1);
            results.addAll(remote);
        }

        return results;
    }

    private List<SearchResult> queryNodes(String q, int hops) {
        List<SearchResult> results = new LinkedList<SearchResult>();

        List<DHTNode> nodes = dhtService.getNodes(q);

        for (DHTNode n : nodes) {
            if (!serverAddress.equals(n.getIPAddress())) {
                results.addAll(queryNode(n, q, hops));
            }
        }

        return results;
    }

    private List<SearchResult> queryNode(DHTNode node, String q, int hops) {
        HttpClient httpClient = new JdkHttpClient();

        String url = "http://" + node.getIPAddress() + ":" + node.getHttpPort() + "/search.json/?q=" + q + "&hops=" + hops;
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

        Store store = new Store();
        store.address = serverAddress;
        store.port = serverPort;
        store.name = storeName;

        for (Product p : products) {
            if (matchProduct(p, q)) {
                SearchResult sr = new SearchResult();
                sr.product = p;
                sr.store = store;
                results.add(sr);
            }
        }

        return results;
    }

    private boolean matchProduct(Product p, String q) {
        for (String keyword : p.keywords) {
            if (q.equals(keyword) || "all".equals(q)) {
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
