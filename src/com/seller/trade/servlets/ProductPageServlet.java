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

package com.seller.trade.servlets;

import com.seller.trade.models.Product;
import com.seller.trade.services.ServiceBroker;

import javax.servlet.http.HttpServletRequest;

public class ProductPageServlet extends WebPageServlet {

    public ProductPageServlet(String command, ServiceBroker broker) {
        super(command, broker, "product.html");
    }

    @Override
    protected String processHtml(HttpServletRequest request, String html) {

        long id = Long.parseLong(getDefaultParameter(request, "id"));
        Product p = broker.getStoreService().getProduct(id);

        html = html.replace("$name", p.name);
        html = html.replace("$description", p.description);
        html = html.replace("$thumbnailUrl", p.thumbnailUrl);
        html = html.replace("$usdPrice", String.valueOf(p.usdPrice));
        html = html.replace("$bitpayData", p.bitpayData);

        return html;
    }
}
