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

import com.seller.trade.models.SearchResult;
import com.seller.trade.services.ServiceBroker;
import com.seller.trade.services.StoreService;
import org.apache.velocity.VelocityContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class SearchResultsPageServlet extends STAbstractServlet {

    private final StoreService storeService;

    public SearchResultsPageServlet(String urlCommand, ServiceBroker broker) {
        super(urlCommand, broker);
        storeService = broker.getStoreService();
    }

    @Override
    protected void handleUncached(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String q = getDefaultParameter(request, "q", "");

        if (q == null || q.isEmpty()) {
            response.sendRedirect("/");
            return; // fast exit
        }

        List<SearchResult> results = storeService.query(q, 1);

        VelocityContext context = new VelocityContext(getBaseContext());
        context.put("results", results);
        broker.getTemplateService().render("search_results.vm", context, response.getWriter());
    }
}
