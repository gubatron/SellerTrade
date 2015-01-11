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

import com.seller.trade.services.ServiceBroker;
import com.seller.trade.services.dht.DHTNode;
import com.seller.trade.services.dht.DHTService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by gubatron on 1/10/15.
 * Returns a list of known peers to connect to.
 * It will cache the last known list as fetching peers from the DHT
 * can take time.
 */
public class LobbyServlet extends STAbstractServlet {

    private final DHTService dhtService;

    public LobbyServlet(String command, ServiceBroker broker) {
        super(command, broker);
        dhtService = broker.getDhtService();
    }

    @Override
    protected void handleUncached(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String output;
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        final List<DHTNode> nodes = dhtService.getNodes();
        if (nodes != null && !nodes.isEmpty()) {
            //Collections.shuffle(nodes);
            StringBuffer buffer = new StringBuffer();

            for (DHTNode node : nodes) {
                buffer.append(node.getIPAddress() + ":" + node.getHttpPort() + "<br>\n");
            }
            output = buffer.toString();//nodes.get(0).getIPAddress();
        }  else {
            output = "-1.-1.-1.-1";
        }
        response.getWriter().println(output);
    }
}