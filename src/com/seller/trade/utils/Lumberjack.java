package com.seller.trade.utils;

import java.util.logging.Logger;

public class Lumberjack {
    public static Logger getLogger(Object obj) {
        
        return Logger.getLogger(obj.getClass().getName());
    }
}
