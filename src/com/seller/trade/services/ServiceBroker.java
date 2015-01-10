package com.seller.trade.services;

import com.seller.trade.core.Configuration;
import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.services.dht.DHTService;
import com.seller.trade.services.dht.DHTServiceImpl;
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
    private final DHTService dhtService;

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

        dhtService = new DHTServiceImpl();

        if (!configuration.getBoolean(ConfigurationKeys.ST_IS_LOBBY_SERVER)) {
            System.out.println("Announcing myself, not lobby.");
            dhtService.announceNode();
        } else {
            System.out.println("Not announcing myself, I'm a lobby server....");
            dhtService.announceNode();
            try {
                System.out.println("Announcing... (I Lied)");
                Thread.sleep(15000);
                System.out.println("Done announcing.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public DHTService getDhtService() { return dhtService; }
}