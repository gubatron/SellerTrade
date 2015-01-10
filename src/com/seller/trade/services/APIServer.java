package com.seller.trade.services;

import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seller.trade.core.Configuration;
import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.servlets.FlashPolicyServer;
import com.seller.trade.servlets.HelloWorldServlet;
import com.seller.trade.servlets.STAbstractServlet;
import org.eclipse.jetty.server.DispatcherType;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * This is the top level Handler, it forwards requests to other handlers depending on the command being received.
 * When I add REDIS to the mix, the redis handler will probably be composed in here to have a caching layer for
 * every request.
 * 
 * @author gubatron
 *
 */
public class APIServer { //extends PunsrAbstractServlet implements Handler {

    public static final String VERSION = "0.6";

    private static final int DEFAULT_PORT = 8686;

    private Map<String, STAbstractServlet> SERVLET_MAP;

    private final ServiceBroker broker;
    
    public APIServer(ServiceBroker serviceBroker) {
        broker = serviceBroker;
        initServletMap();
    }
    
    /*
     * All these guys return JSON output, which should be cached using an
     * all purpose cache technology like Redis.
     */
    private void initServletMap() {
        SERVLET_MAP = new HashMap<String, STAbstractServlet>();
        SERVLET_MAP.put("hello", new HelloWorldServlet("hello", broker));
    }

    @SuppressWarnings("rawtypes")
    public String getURLCommandNameForHandler(Class clazz) {

        Collection<STAbstractServlet> servlets = SERVLET_MAP.values();

        for (STAbstractServlet servlet : servlets) {
            if (servlet.getClass().equals(clazz)) {
                return servlet.getURLCommandName();
            }
        }

        return null;
    }

    private void addServletsToContextHandler(ServletContextHandler context) {
       
        for (Entry<String, STAbstractServlet> entry : SERVLET_MAP.entrySet()) {
            String contextPath =  "/" + entry.getKey() + "/";
            
            if(contextPath.equals("///")) {
                contextPath = "/";
            }
            
            System.out.println("key: ["+entry.getKey()+"] value: ["+entry.getValue().getClass().getName()+"] contextPath: ["+contextPath+"]");
            
            context.addServlet(new ServletHolder(entry.getValue()),contextPath);
        }
    }


    public static void main(String[] args) {
        ServiceBroker broker = null;
        Configuration configuration = null;
        Logger MAIN_LOG = Logger.getLogger("APIServer.main");
        try {
            configuration = new Configuration(args[0]);
            broker = new ServiceBroker(configuration);
        } catch (final Exception e) {
            MAIN_LOG.log(Level.SEVERE, "config.conf could not be found or read.",e);
            return;
        }

        int port = DEFAULT_PORT;
        try {
            port = configuration.getInt(ConfigurationKeys.ST_API_PORT);
        } catch (Exception e) {
            MAIN_LOG.log(Level.WARNING, "Invalid port on config.conf, using default port " + port);
        }

        APIServer apiServer = new APIServer(broker);
        Server server = new Server(port) {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                super.handle(target, baseRequest, request, response);
                
                System.out.println("Server.handle. target: ["+ target +"]");
                System.out.println("Server.handle. server name: [" + baseRequest.getServerName() + "]");
                System.out.println("\n");
            }
        };
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        
        Set<DispatcherType> requestDispatcherEnumSet = EnumSet.allOf(DispatcherType.class);
        //context.addFilter(APIServerFilter.class, "/*",(EnumSet<DispatcherType>) requestDispatcherEnumSet);
        server.setHandler(context);
        
        //automatically add all the Servlets.
        apiServer.addServletsToContextHandler(context);

        FlashPolicyServer fps = new FlashPolicyServer(8430);
        fps.start();

        try {
            MAIN_LOG.log(Level.ALL, "Starting SellerTrade APIServer on port " + port);
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}