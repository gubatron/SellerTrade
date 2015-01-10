package com.seller.trade.servlets;

import com.seller.trade.services.ServiceBroker;
import com.seller.trade.services.dht.DHTNode;
import com.seller.trade.services.dht.DHTService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
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
            //nodes.stream().forEach((d) -> { buffer.append(d.getIPAddress() + "\n");});
            for (DHTNode node : nodes) {
                buffer.append(node.getIPAddress() + "<br>\n");
            }
            output = buffer.toString();//nodes.get(0).getIPAddress();
        }  else {
            output = "-1.-1.-1.-1";
        }
        response.getWriter().println(output);
    }
}