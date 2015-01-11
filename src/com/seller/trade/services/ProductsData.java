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

import com.seller.trade.models.Product;

import java.util.Arrays;
import java.util.List;

public final class ProductsData {

    private ProductsData() {
    }

    public static Product product1() {
        Product p = new Product();

        p.id = 1;
        p.name = "T-Shirt (Male, Different Colors)";
        p.description = "Made in the USA, 100% cotton. FrostWire male t-shirt in blue, yellow and charcoal.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers.";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/36/images/208/blue__76605.1359041577.220.290.png";
        p.usdPrice = 19.99f;
        p.bitpayData = "ADimSwMVgoTVnyH4j7Ff8z";

        p.keywords = new String[]{"tshirt", "male tshirt"};

        return p;
    }

    public static Product product2() {
        Product p = new Product();

        p.id = 2;
        p.name = "T-Shirt (Female, Different Colors)";
        p.description = "Made in the USA, 100% cotton. FrostWire female t-shirt in scarlet, indigo and turquoise.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers.";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/37/images/196/scarlet__09723.1359039068.220.290.png";
        p.usdPrice = 19.99f;
        p.bitpayData = "MozGXCZr9SN69RtCyzyG7y";

        p.keywords = new String[]{"tshirt", "female tshirt"};

        return p;
    }

    public static Product product3() {
        Product p = new Product();

        p.id = 3;
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
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/45/images/249/new_hoodie_front__18798.1372088429.220.290.png";
        p.usdPrice = 32.99f;
        p.bitpayData = "Q8YMtvpfHVBdPHc8WuTGmE";

        p.keywords = new String[]{"hoodie", "unisex", "zip hoodie"};

        return p;
    }

    public static Product product4() {
        Product p = new Product();

        p.id = 4;
        p.name = "Six FrostWire Stickers";
        p.description = "Support FrostWire, the free and open source project, with your $3 donation. You can always purchase multiples or go directly to our PayPal, Bitcoin or Degecoin donation page to choose the amount.\n" +
                "\n" +
                "Tag your mobile device as FrostWire friendly with the new, perfectly sized mini FrostWire stickers and spread the word outdoors with the larger FrostWire logo!\n" +
                "\n" +
                "Your order will include three (3) standard FrostWire Logo stickers (2,5 inch in diameter) and three (3) mini FrostWire stickers (1,5 inch in diameter).\n" +
                "\n" +
                "They don't scratch, don't fade for years in full sunlight and peel of cleanly from most surfaces.\n" +
                "\n" +
                "Place them indoors or outdoors! The standard sized stickers are just the right size to cover your laptop's logo or mark areas with free Wi-Fi to invite others to share using FrostWire. The mini FrostWire stickers are ideal for mobile devices and other small, personal surfeces. \n" +
                "\n" +
                "Be sure to take pictures and share them with us on our FrostWire Facebook page or send them by email to contact@frostwire.com. We love to show off all the fun places our stickers travel to!";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/39/images/206/sticker__87663.1359040458.220.290.jpg";
        p.usdPrice = 2.99f;
        p.bitpayData = "GQFmxkBnixAgtqiJR4UUzE";

        p.keywords = new String[]{"frostwire", "stickers", "sticker"};

        return p;
    }

    public static Product product5() {
        Product p = new Product();

        p.id = 5;
        p.name = "Zip Hoodie (Unisex)";
        p.description = "Super-soft and lightweight fleece hoodie in Charcoal Heather.\n" +
                "\n" +
                "Music player/smartphone holder inside right pocket.\n" +
                "\n" +
                "Inner pocket hole to pass headphones cables inside the hoodie.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers.";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/38/images/205/hoodie_front_2__50007.1359040277.220.290.png";
        p.usdPrice = 19.99f;
        p.bitpayData = "8inBRtF4viiaTpwubk8hCr";

        p.keywords = new String[]{"hoodie", "sweater", "unisex", "zip hoodie"};

        return p;
    }

    public static Product product6() {
        Product p = new Product();

        p.id = 6;
        p.name = "T-Shirt (Unisex, Light Blue, Limited Quantity)";
        p.description = "Made in the USA, 100% cotton. FrostWire unisex t-shirt in light blue. The last batch of our original design, limited quantities.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers.";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/41/images/213/Tshirt_original_blue3__47476.1361915783.220.290.jpg";
        p.usdPrice = 9.99f;
        p.bitpayData = "QpXGeBPmn7PTdTGZofQu9X";

        p.keywords = new String[]{"tshirt", "blue tshirt", "unisex"};

        return p;
    }

    public static Product product7() {
        Product p = new Product();

        p.id = 7;
        p.name = "T-Shirt (Unisex, Black, Limited Quantity)";
        p.description = "Made in the USA, 100% cotton. FrostWire unisex t-shirt in black. The last batch of our original design, limited quantities.\n" +
                "\n" +
                "Each order includes 10 free FrostWire stickers.";
        p.thumbnailUrl = "http://cdn2.bigcommerce.com/server100/vhyaryj/products/40/images/221/black_final_final_male__54132.1361986010.220.290.jpg";
        p.usdPrice = 9.99f;
        p.bitpayData = "971Qch9cXvp3o6jrDi1a5g";

        p.keywords = new String[]{"tshirt", "black tshirt", "unisex"};

        return p;
    }

    public static Product product8() {
        Product p = new Product();

        p.id = 8;
        p.name = "FrostWire 16GB USB 2.0 Drive";
        p.description = "This 16GB USB 2.0 Drive features a sleek stainless steal design with a subtly engraved FrostWire logo and a key ring to attach to your key chain or a backpack. The 16GB flash memory allows you to save thousands of music files or pictures or a few movies, depending on the file size.\n" +
                "\n" +
                "Whether you are a seasoned FrostWire user that needs to take his files on the go or a fan that would be proud to sport the FrostWire logo on his portable drive, this one is for you!\n" +
                "\n" +
                " \n" +
                "\n" +
                "Each order comes with 10 Free FrostWire stickers (5 mini & 5 standard size)! \n" +
                "\n" +
                " \n" +
                "\n" +
                "FREE US Shipping Included!";
        p.thumbnailUrl = "http://shop.frostwire.com/templates/__custom/images/usb_home.jpg";
        p.usdPrice = 24.99f;
        p.bitpayData = "CMNjceFDHi2mba1HQyDiXF";

        p.keywords = new String[]{"frostwire", "usb", "usb drive"};

        return p;
    }

    public static List<Product> set1() {
        return Arrays.asList(product1(), product2(), product3(), product4());
    }

    public static List<Product> set2() {
        return Arrays.asList(product5(), product6(), product7(), product8());
    }
}
