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

package com.seller.trade.services;

import com.seller.trade.core.Configuration;
import com.seller.trade.core.ConfigurationKeys;
import com.seller.trade.services.dht.DHTService;
import com.seller.trade.services.dht.DHTServiceImpl;
import com.seller.trade.utils.Lumberjack;

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
    private final StoreService storeService;
    private final TemplateService templateService;

    private final String serverIp;

    public ServiceBroker(Configuration configuration) {
        System.out.println("Starting ServiceBroker...");
        this.configuration = configuration;
        LOG = Lumberjack.getLogger(this);
        serverIp = configuration.getString(ConfigurationKeys.ST_SERVER_IP);
        int tcpPort = configuration.getInt(ConfigurationKeys.ST_SERVER_PORT);

        dhtService = new DHTServiceImpl(configuration.getBoolean(ConfigurationKeys.ST_USE_LAN_MAPPINGS));
        if (!configuration.getBoolean(ConfigurationKeys.ST_IS_LOBBY_SERVER)) {
            System.out.println("Announcing myself, not lobby.");
            dhtService.announceNode(tcpPort);
        } else {
            System.out.println("Not announcing myself, I'm a lobby server....");
        }

        storeService = new StoreService(configuration, dhtService);
        storeService.announceProducts();

        templateService = new TemplateService(configuration);
        System.out.println("ServiceBroker started.");
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public DHTService getDhtService() { return dhtService; }

    public StoreService getStoreService() { return storeService; }

    public TemplateService getTemplateService() { return templateService; }
}
