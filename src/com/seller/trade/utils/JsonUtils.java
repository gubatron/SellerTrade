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
