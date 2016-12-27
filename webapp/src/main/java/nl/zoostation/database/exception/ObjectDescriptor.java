package nl.zoostation.database.exception;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author valentinnastasi
 */
public final class ObjectDescriptor {

    private String name;
    private Map<String, String> characteristics;

    private ObjectDescriptor() {
        characteristics = new HashMap<>(1);
    }

    public static ObjectDescriptor ofName(String name) {
        ObjectDescriptor objectDescriptor = new ObjectDescriptor();
        objectDescriptor.name = name;
        return objectDescriptor;
    }

    public static ObjectDescriptor ofName(Class<?> clazz) {
        return ofName(clazz.getSimpleName());
    }

    public ObjectDescriptor with(String key, Object value) {
        characteristics.put(key, value != null ? value.toString() : "NULL");
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        if (MapUtils.isNotEmpty(characteristics)) {
            stringBuilder.append(" (");
            String details = characteristics.entrySet().stream().map(e -> e.getKey() + " = " + (e.getValue() != null ? e.getValue() : "NULL"))
                    .collect(Collectors.joining("; "));
            stringBuilder.append(details);
            stringBuilder.append(")");
        }
        return stringBuilder.toString();
    }
}
