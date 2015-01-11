package com.seller.trade.servlets;

import com.seller.trade.services.ServiceBroker;
import org.apache.velocity.VelocityContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The home page on non-lobby servers. A simple, google-like search form.
 * Created by gubatron on 1/11/15.
 */
public class SearchLandingPageServlet extends STAbstractServlet {
    public SearchLandingPageServlet(String urlCommand, ServiceBroker broker) {
        super(urlCommand, broker);
    }

    @Override
    protected void handleUncached(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final VelocityContext context = new VelocityContext(getBaseContext());
        broker.getTemplateService().render("search_landing.vm", context, response.getWriter());
    }
}
