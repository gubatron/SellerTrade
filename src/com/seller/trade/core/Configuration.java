package com.seller.trade.core;

import com.seller.trade.utils.Lumberjack;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Configuration {

    private static final Logger LOG = Lumberjack.getLogger(Configuration.class);

    private static final String ST_CONF_ENV = "ST_CONF";

    private final Properties properties;

    public Configuration(String filename) {
        properties = new Properties();

        try {
            properties.load(new FileInputStream(filename));
        } catch (Throwable e) {
            throw new RuntimeException("Can't run without configuration", e);
        }
    }

    public Configuration() {
        this(System.getenv(ST_CONF_ENV));
    }

    public String getString(String key) {
        String r = "";
        
        try{
            r = properties.getProperty(key);
        } catch (Throwable e) {
            LOG.log(Level.SEVERE, String.format("Error reading configuration key %s", key), e);
        }
        
        return r;
    }

    public int getInt(String key) {
        int r = 0;

        try {
            r = Integer.parseInt(properties.getProperty(key));
        } catch (Throwable e) {
            LOG.log(Level.SEVERE, String.format("Error reading configuration key %s", key), e);
        }

        return r;
    }
    
    public float getFloat(String key) {
        float r = 0f;

        try {
            r = Float.parseFloat(properties.getProperty(key));
        } catch (Throwable e) {
            LOG.log(Level.SEVERE, String.format("Error reading configuration key %s", key), e);
        }

        return r;
    }

    public boolean getBoolean(String key) {
        boolean r = false;

        try {
            r = Boolean.parseBoolean(properties.getProperty(key));
        } catch (Throwable e) {
            LOG.log(Level.SEVERE, String.format("Error reading configuration key %s", key), e);
        }

        return r;
    }

    public List<String> getList(String key) {

        List<String> results = new ArrayList<>();
        String property = null;
        String[] split = null;

        try {
            property = properties.getProperty(key);
            
            if (property == null) {
                return null;
            }
            
            split = property.split(",");
            results = Arrays.asList(split);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, String.format("Error reading configuration key %s", key), e);
        }

        return results;
    }

    public long getLong(String key) {
        long r = 0;

        try {
            r = Long.parseLong(properties.getProperty(key));
        } catch (Throwable e) {
            LOG.log(Level.SEVERE, String.format("Error reading configuration key %s", key), e);
        }

        return r;
    }
}
