package nl.zoostation.database.app.tools;

import nl.zoostation.database.exception.ErrorMessage;
import nl.zoostation.database.exception.InvalidParameterException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author valentinnastasi
 */
public final class ObjectUtils {

    private ObjectUtils() {
    }

    public static boolean isMock(Object o) {
        if (Objects.isNull(o)) {
            throw new InvalidParameterException(ErrorMessage.NULL_PARAMETER, ObjectUtils.class.getName(), "o");
        }

        return isMock(o.getClass());
    }

    public static boolean isMock(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            throw new InvalidParameterException(ErrorMessage.NULL_PARAMETER, ObjectUtils.class.getName(), "clazz");
        }

        return StringUtils.containsIgnoreCase(clazz.getSimpleName(), "mock");
    }

}
