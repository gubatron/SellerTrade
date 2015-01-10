package com.seller.trade.servlets;

import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.models.SearchResult;
import com.seller.trade.models.SearchResultList;
import com.seller.trade.services.ServiceBroker;
import com.seller.trade.services.StoreService;
import com.seller.trade.services.dht.DHTNode;
import com.seller.trade.services.dht.DHTService;
import com.seller.trade.utils.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by gubatron on 1/10/15.
 */
public class SearchServlet extends STAbstractServlet {

    private final DHTService dhtService;
    private final StoreService storeService;

    public SearchServlet(String command, ServiceBroker broker) {
        super(command, broker);
        dhtService = broker.getDhtService();
        storeService = broker.getStoreService();
    }

    @Override
    protected void handleUncached(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String output;
        response.setContentType("text/html");
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
