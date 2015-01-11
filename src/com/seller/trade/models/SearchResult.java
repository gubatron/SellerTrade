package com.seller.trade.models;

public class SearchResult {

    public Product product;
    public Store store;

    public String getName() {
        return product.name;
    }
}
