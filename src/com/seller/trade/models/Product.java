package com.seller.trade.models;

/**
 * Created by gubatron on 1/10/15.
 */
public class Product {
    public long id;
    public String name;
    public String description;
    public String[] keywords;
    public String thumbnailUrl;

    /** No price volatility anxiety, thank you BitPay. */
    public float usdPrice;

    //TODO: list of pictures urls, for more product pictures.
}
