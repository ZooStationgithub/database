package nl.zoostation.database.app.tools;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("unchecked")
public class MapBuilder<K, V> {

    private Map<K, V> map;

    private <M extends Map<K, V>> MapBuilder(Class<M> mapImplementationClass) {
        try {
            map = mapImplementationClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> MapBuilder<K, V> newHashMap() {
        return new MapBuilder<>(HashMap.class);
    }

    public static <K, V> MapBuilder<K, V> newTreeMap() {
        return new MapBuilder<>(TreeMap.class);
    }

    public static <K, V> MapBuilder<K, V> newLinkedHashMap() {
        return new MapBuilder<>(LinkedHashMap.class);
    }

    public Map<K, V> build() {
        return map;
    }

    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

}
