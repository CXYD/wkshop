package net.jeeshop.core.util;


import net.jeeshop.core.util.transform.MapEntryTransform;
import net.jeeshop.core.util.transform.Transform;

import java.util.*;

/**
 * collection/map utils
 *
 * @author dinguangx@163.com
 * @date 11/23/15 1:44 PM
 */
public class CollectionUtils {
    /**
     * convert a list to another one
     *
     * @param list
     * @param transform
     * @param <T>       source data type
     * @param <D>       target data type
     * @return
     */
    public static <T, D> List<D> convert(Collection<T> list, Transform<T, D> transform) {
        if (list == null) {
            return null;
        }
        List<D> result = new ArrayList<D>();
        for (T record : list) {
            if (transform.apply(record)) {
                result.add(transform.convert(record));
            }
        }
        return result;
    }

    /**
     * convert a list to a map
     *
     * @param list
     * @param transform
     * @param <T>       source list data type
     * @param <K>       target map key data type
     * @param <V>       target map value data type
     * @return
     */
    public static <T, K, V> Map<K, V> convert(Collection<T> list, MapEntryTransform<T, K, V> transform) {
        if (list == null) {
            return null;
        }
        Map<K, V> result = new HashMap<K, V>();
        for (T record : list) {
            if (transform.apply(record)) {
                Pair<K, V> entry = transform.convert(record);
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

}
