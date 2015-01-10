package com.seller.trade.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Simple JSON utility class based on google-gson.
 * 
 * Visit google-gson: {@link http://code.google.com/p/google-gson/} for more information.
 * 
 * @author gubatron
 * @author aldenml
 * 
 */
public final class JsonUtils {

    private static final Gson gson = new GsonBuilder().create();

    private JsonUtils() {
    }

    /**
     * This method serializes the specified object into its equivalent Json
     * representation.
     * 
     * This method should only be used when the specified object is not a generic type.
     * 
     * @param obj the object for which Json representation is to be created
     * @return Json representation of obj
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * This method deserializes the specified Json into an object of the specified class.
     * 
     * This method should not be used if the desired type is a generic type.
     * 
     * @param <T> the type of the desired object
     * @param json the string from which the object is to be deserialized
     * @param classOfT the class of T
     * @return an object of type T from the string
     */
    public static <T> T toObject(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
