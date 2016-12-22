package nl.zoostation.database.annotations;

import java.lang.annotation.*;

/**
 * @author valentinnastasi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface NotEmpty {
}
