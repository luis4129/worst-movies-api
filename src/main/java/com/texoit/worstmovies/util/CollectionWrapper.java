package com.texoit.worstmovies.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CollectionWrapper {

    public static <T> Map<String, Collection<T>> wrap(String name, Collection<T> collection) {
        Map map = new HashMap();
        map.put(name, collection);
        return map;
    }

}
