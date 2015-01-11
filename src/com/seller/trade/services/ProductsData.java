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
        p.usdPrice = 0.05f;
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
        return Arrays.asList(product1(), product2(), product3(), product4(), product5(), product6(), product7(), product8());
    }

    public static List<Product> set2() {
        return Arrays.asList(amz9(), amz10(), amz11(), amz12(), amz13(), amz14());
    }

    public static Product amz9() {
        Product p = new Product();

        p.id = 9;
        p.name = "Paul Mitchell Tea Tree Special Shampoo-Conditioner";
        p.description = "Ordinary Shampoos need not apply! Get a head start every morning and experience the tingle of invigorating tea tree oil, peppermint and lavender. Help wash away impurities as hair becomes fresh and clean, full of vitality and luster. Color safe and ideal for all hair types \n" +
                "\n" +
                "Your hair deserves special treatment. Experience the natural magic of tea tree oil, peppermint and lavender. Enjoy an invigorating tingle that leaves hair and scalp looking and feeling healthy. The moisturizing botanical blend tackles tangles for instant manageability, Color safe and ideal for all hair types.";
        p.thumbnailUrl = "http://ecx.images-amazon.com/images/I/31xtH-5pMjL._SL190_CR0,0,190,246_.jpg";
        p.usdPrice = 49.99f;
        p.bitpayData = "YVPK42Se8LSFZ5U6uibF8K";

        p.keywords = new String[]{"shampoo", "conditioner"};

        return p;
    }

    public static Product amz10() {
        Product p = new Product();

        p.id = 10;
        p.name = "Pureology Hydrate Shampoo and Hydrate Conditioner";
        p.description = "HYDRATETM Shampoo 8.5 oz Concentrated Formula FOR DRY COLOUR-TREATED HAIR Gently cleanse without stripping colour with this concentrated moisturizing shampoo. ZeroSulfate formula with the exclusive AntiFadeComplex delivers a rich lather, while infusing hair with essential hydration and colour retention. FEATURES ZeroSulfate Sulfate-free, salt-free, colour-preserving formula derived from coconut, corn and sugar Natural Plant Extracts Rose, Sandalwood and Green Tea Signature Aromatherapy Blend Ylang Ylang, Bergamot, Anise and Patchouli BENEFITS Concentrated formula Luxurious lather, easy rinsing Extra gentle cleansers Does not strip colour For concentrated natural conditioning Luxurious fragrance for a multisensorial, spa-like experience HOW TO USE Apply to wet hair and lather. Rinse. Repeat if necessary. HYDRATETM Condition 8.5 oz Concentrated Formula FOR DRY COLOUR-TREATED HAIR Reveals soft, lustrous hair while protecting colour vibrancy with this luxurious, concentrated daily hydrating conditioner. The exclusive AntiFadeComplexmaximizes colour retention. FEATURES Jojoba Esters and Shea Butter Natural Plant Extracts Peppermint, Sage and Rosemary Signature Aromatherapy Blend Peppermint and Corn Mint Essential Oils BENEFITS Intensely hydrate without weighing hair down For concentrated natural conditioning Awakens the senses with an energizing, refreshing fragrance Replenishes parched hair with movement and manageability Rehydrates lightened and chemically relaxed hair Minimizes static HOW TO USE Massage gently into hair and scalp. Wait 1-2 minutes. Rinse.";
        p.thumbnailUrl = "http://ecx.images-amazon.com/images/I/21sbh-1F1GL._SL190_CR0,0,190,246_.jpg";
        p.usdPrice = 44.90f;
        p.bitpayData = "nuSuRH8KfCQiNi6gnsFtH";

        p.keywords = new String[]{"shampoo", "conditioner", "hydrate"};

        return p;
    }

    public static Product amz11() {
        Product p = new Product();

        p.id = 11;
        p.name = "Brazilian Blowout Anti-Frizz Shampoo & Conditioner";
        p.description = "Brazilian Blowout Anti-Frizz Shampoo & Conditioner 12-ounce bottles Brazilian Blowout Acai Anti-Frizz Shampoo: Sulfate free formula, gently cleanses, retains moisture, humidity resistant, and color safe Acai Anti-Frizz Shampoo is a sulfate-free formula that thoroughly cleanses the hair while maintaining vital moisture for increased strength and radiant shine. An exclusive Brazilian Super-Nutrient Complex infuses the hair with a rich source of amino acids to help strengthen while locking in moisture and locking out humidity. The end result is smooth, frizz-free, radiant hair. Acai Anti-Frizz Conditioner is a deep moisturizing conditioner that infuses the hair with the vital moisture and nutrients needed to maintain the optimal strength and health of the hair. An exclusive Brazilian Super Nutrient Complex fortifies and repairs the hair while locking in moisture and locking out humidity. The end result is healthy, frizz-free, radiant hair.";
        p.thumbnailUrl = "http://ecx.images-amazon.com/images/I/41kD4%2BRVIsL._SL246_SX190_CR0,0,190,246_.jpg";
        p.usdPrice = 36.16f;
        p.bitpayData = "N1YHRPnBUUxfMW3xwbPp2D";

        p.keywords = new String[]{"shampoo", "conditioner", "frizz", "anti frizz"};

        return p;
    }

    public static Product amz12() {
        Product p = new Product();

        p.id = 12;
        p.name = "Kiss My Face Naked Pure Olive Oil Bar Soap";
        p.description = "Made with 86% pure olive oil for superior moisturizing. pack of three 4 ounce bars is a 33% savings over individual bar purchase. A natural cleansing and moisturizing soap. For centuries, the people of the Mediterranean have recognized the benefits of olive oil to the skin. Long lasting and made from the purest natural ingredients, it is a superb moisturizer suitable for all skin types. It's simple and pure. This moisturizing bar was created without animal ingredients, artificial colors, unnecessary chemicals or animal testing. Made in Greece.";
        p.thumbnailUrl = "http://ecx.images-amazon.com/images/I/51Py-y8085L._SL190_SY246_CR0,0,190,246_.jpg";
        p.usdPrice = 4.99f;
        p.bitpayData = "E1wGbPx2nHni9QdR51bMGP";

        p.keywords = new String[]{"soap", "oil bar"};

        return p;
    }

    public static Product amz13() {
        Product p = new Product();

        p.id = 13;
        p.name = "Lacoste Eau de Lacoste - Rouge Eau de Toilet";
        p.description = "Eau de Lacoste L.12.12 Rouge is a lively fragrance built around sparkling red tea beautifully contrasted with a blend of ginger, black pepper and cardamom ensuing in a masculine scent with an energetic spirit.\n" +
                "Benefits\n" +
                "Energetic, dynamic and playful, L.12.12 Rouge captures the spirit and authenticity of the red L.12.12 polo shirt.";
        p.thumbnailUrl = "http://ecx.images-amazon.com/images/I/41jGc5OIEQL._SL246_SX190_CR0,0,190,246_.jpg";
        p.usdPrice = 69.00f;
        p.bitpayData = "8adCNuNErqnTa6nHMEFtPz";

        p.keywords = new String[]{"fragance", "man fragance", "lacoste"};

        return p;
    }

    public static Product amz14() {
        Product p = new Product();

        p.id = 14;
        p.name = "Lacoste Sensuelle Eau de Parfum for Women";
        p.description = "Eau De Lacoste Sensuelle. The spirit of Lacoste in a sensual fragrance that captures the natural intensity of soft floral notes.\n" +
                "Benefits\n" +
                "Opens with the tartness of ripe, lush blackcurrant while the base finishes with the sensuality of nougatine accord.";
        p.thumbnailUrl = "http://ecx.images-amazon.com/images/I/41yotIOLueL._SL246_SX190_CR0,0,190,246_.jpg";
        p.usdPrice = 82.00f;
        p.bitpayData = "KoPT9uL93egDubiqksQMzZ";

        p.keywords = new String[]{"fragance", "women fragance", "lacoste"};

        return p;
    }
}
