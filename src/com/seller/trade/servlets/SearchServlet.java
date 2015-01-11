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

import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.models.SearchResult;
import com.seller.trade.models.SearchResultList;
import com.seller.trade.services.ServiceBroker;
import com.seller.trade.services.StoreService;
import com.seller.trade.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class SearchServlet extends STAbstractServlet {

    private final StoreService storeService;

    public SearchServlet(String command, ServiceBroker broker) {
        super(command, broker);
        storeService = broker.getStoreService();
    }

    @Override
    protected void handleUncached(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/json");
        response.setStatus(HttpServletResponse.SC_OK);

        String q = getDefaultParameter(request, "q", "");
        String hops = getDefaultParameter(request, "hops", String.valueOf(broker.getConfiguration().getInt(ConfigurationKeys.ST_SEARCH_HOPS)));

        if (q == null || q.isEmpty()) {
            return; // fast exit
        }

        if (hops == null || hops.isEmpty()) {
            return; // fast exit
        }

        List<SearchResult> results = storeService.query(q, Integer.parseInt(hops));

        SearchResultList list = new SearchResultList();
        list.results = results;

        response.getWriter().println(JsonUtils.toJson(list));
    }
}
