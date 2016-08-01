package net.jeeshop.biz.finance;

import java.util.Map;

/**
 * @author dylan
 * @date 16/3/10 22:35
 * Email: dinguangx@163.com
 */
public class Maps {
    public static <K,V> Map<K, V> of(K k1, V v1,
                                     K k2, V v2,
                                     K k3, V v3,
                                     K k4, V v4) {
        Map<K,V> map = com.google.common.collect.Maps.newHashMap();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }
    public static <K,V> Map<K, V> of(K k1, V v1,
                                     K k2, V v2,
                                     K k3, V v3,
                                     K k4, V v4,
                                     K k5, V v5) {
        Map<K,V> map = com.google.common.collect.Maps.newHashMap();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }
}
