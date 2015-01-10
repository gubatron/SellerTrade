package com.seller.trade.services;

import com.seller.trade.core.Configuration;
import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.models.Product;
import com.seller.trade.services.dht.DHTService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        final int httpPort = configuration.getInt(ConfigurationKeys.ST_API_PORT);
        loadProducts();

        for (Product p : products) {
            dhtService.announceKeywords(Arrays.asList(p.keywords), httpPort);
        }
    }

    /**
     * In the future we'll have here a parametric interface to load products from
     * whatever e-commerce solution you're already using.
     *
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
        p.keywords = new String[] {"hoodie","frostwire","sweater","unisex", "zip hoodie"};
        products.add(p);

        p = new Product();
        p.id = 2;
        p.name = "T-Shirt (Male, Different Colors)";
        p.description = "Made in the USA, 100% cotton. FrostWire male t-shirt in blue, yellow and charcoal.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers.";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/36/images/208/blue__76605.1405366443.300.400.png?c=2";
        p.usdPrice = 19.99f;
        p.keywords = new String[] {"tshirt", "frostwire tshirt", "shirt", "male tshirt"};

        products.add(p);
    }
}
