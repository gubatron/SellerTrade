package com.seller.trade.servlets;

import com.seller.trade.services.ServiceBroker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gubatron on 1/10/15.
 */
public class HelloWorldServlet extends STAbstractServlet {
    public HelloWorldServlet(String command, ServiceBroker broker) {
        super(command, broker);
    }

    @Override
    protected void handleUncached(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Hello World");
    }
}
