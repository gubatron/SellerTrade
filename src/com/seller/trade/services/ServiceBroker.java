package com.seller.trade.services;

import com.seller.trade.core.Configuration;
import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.utils.Lumberjack;

import java.util.List;
import java.util.logging.Logger;

/**
 * Extend from this class if you need to access the MongoMapper goodies.
 *
 * (In some time passes and we don't need to add any abstract methods here
 * I will start composing from DataService instead of extending from it)
 * 
 * @author gubatron
 *
 */
public final class ServiceBroker {

    protected final Logger LOG;
    private final Configuration configuration;

    private final List<String> servers;

    public ServiceBroker(Configuration configuration) {
        this(configuration, true, true, true);
    }

    public ServiceBroker(Configuration configuration, boolean initWordIndexer, boolean initTwitterClient, boolean initFacebookClient) {
        System.out.println("Starting ServiceBroker...");
        this.configuration = configuration;
        LOG = Lumberjack.getLogger(this);
        servers = configuration.getList(ConfigurationKeys.ST_API_SERVERS_IPS);
        System.out.println("ServiceBroker started.");
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}