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