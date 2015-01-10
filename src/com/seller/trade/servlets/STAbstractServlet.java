package com.seller.trade.servlets;

/**
 * Created by gubatron on 1/10/15.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.services.ServiceBroker;
import com.seller.trade.utils.Lumberjack;
import com.seller.trade.utils.StringUtils;
import org.eclipse.jetty.server.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Jetty Base handler that holds all the common functionality for the specific handlers.
 * <p/>
 * This handler is able to wrap responses, and parse most of the URL commands.
 *
 * @author gubatron
 */
public abstract class STAbstractServlet extends HttpServlet {

    private static final long serialVersionUID = 2583257510797814586L;

    /**
     * Most generic pattern, basically letters, numbers, "-" and "_"
     */
    protected static Pattern URL_COMMAND_PATTERN = Pattern.compile("^/([a-zA-Z0-9-_]+)(.*)");

    /**
     * Let extending handlers set what http://.../<command-name>/ to use.
     * This allows for the URL configuration of the handler upon instantiation
     * since we pass the command name on the constructor.
     * <p/>
     * If we want to handle 2 or more commands with the same handler,
     * we just create more instances and they'll be handled separately.
     * <p/>
     * Try to not maintain state if you need to handle two different commands
     * with the same handler, or refactor to allow handlers to use more than
     * one command on initParsingParameters()
     *
     * @param urlCommandName
     */
    private final String urlCommandName;

    protected Logger LOG;

    protected ServiceBroker broker;

    public STAbstractServlet(String urlCommand, ServiceBroker broker) {
        //DM = MockDataMediator.instance();

        LOG = Lumberjack.getLogger(this);
        urlCommandName = urlCommand;

        this.broker = broker;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(getURLCommandName() + " GET: " + request.getPathInfo());
        handle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(getURLCommandName() + " POST: " + request.getPathInfo());
        handle(request, response);
    }

    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (broker.getConfiguration().getBoolean(ConfigurationKeys.ST_SEND_ACCESS_CONTROL_ALLOW_ORIGIN)) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }

        if (request.getPathInfo() != null && request.getPathInfo().equals("/")) {
            sendWrappedJSONResponse(request, response, new ArrayList<String>());
            return;
        }

        if (request.getPathInfo() != null && request.getPathInfo().equals("/favicon.ico")) {
            return;
        }

        handleUncached(request, response);
    }

    /**
     * This is what you have to implement on your handler.
     * The output of this will be cached for you by my implementation of handle()
     *
     * @param request
     * @param response
     */
    protected abstract void handleUncached(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    /**
     * Sends the JSON string as a response. Sends the right content type headers for JSON.
     * Assumes you're sending well formed JSON, there's no validation.
     *
     * @param response
     * @param jsonOutput
     * @throws IOException
     */
    protected void sendJSONResponse(HttpServletRequest request, HttpServletResponse response, String jsonOutput) throws IOException {
        response.getWriter().println(jsonOutput);
    }

    protected void prepareJSONHttpResponse(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Server", "SellerTrade (" + APIServer.VERSION + ")");
    }

    /**
     * Converts the given object into a json string and sends it.
     *
     * @param response
     * @param outputObject
     * @throws IOException
     */
    protected void sendJSONResponse(HttpServletRequest request, HttpServletResponse response, Object outputObject) throws IOException {
        Gson gson = null;
        gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(outputObject);
        sendJSONResponse(request, response, jsonOutput);
    }

    /**
     * Use this method when you need to send a List<T> as a response.
     * It'll wrap the list in a ResponseWrapper, and the resulting object
     * will look like:
     * {
     * numResults : N,
     * results: [ T1, T2, ... ,TN ]
     * }
     *
     * @param response
     * @param results
     * @throws IOException
     */
    protected void sendWrappedJSONResponse(HttpServletRequest request, HttpServletResponse response, List results) throws IOException {
        ResponseWrapper wrappedResponse = new ResponseWrapper(results);
        sendJSONResponse(request, response, wrappedResponse);
    }

    protected void sendWrappedJSONResponse(HttpServletRequest request, HttpServletResponse response, List results, Map<String, String> map) throws IOException {
        ResponseWrapper wrappedResponse = new ResponseWrapper(results, map);
        sendJSONResponse(request, response, wrappedResponse);
    }

    /**
     * DO NOT use <Types>, Gson() chokes on them.
     */
    public class ResponseWrapper {
        public int numResults;
        public List results;
        public Map<String, String> map;
        public String version = APIServer.VERSION;

        public ResponseWrapper(List results) {
            this.results = results;
            if (results != null) {
                numResults = results.size();
            }
        }

        public ResponseWrapper(List results, Map<String, String> map) {
            this.results = results;
            if (results != null) {
                numResults = results.size();
            }

            this.map = map;
        }

        public ResponseWrapper(int numResults) {
            this.numResults = numResults;
        }
    }

    public String getURLCommandName() {
        return urlCommandName;
    }

    protected String getDefaultParameter(HttpServletRequest request, String paramName) {
        return getDefaultParameter(request, paramName, null);
    }

    protected String getDefaultParameter(HttpServletRequest request, String paramName, String defaultValue) {
        String result = request.getParameter(paramName);

        if (StringUtils.isNullOrEmpty(result)) {
            result = defaultValue;
        } else {
            try {
                result = URLDecoder.decode(result, "utf8");
            } catch (UnsupportedEncodingException e) {
                System.out.println("===");
                e.printStackTrace();
                System.out.println("===");
            }
        }
        return result;
    }

    public final ServiceBroker getBroker() {
        return broker;
    }
}