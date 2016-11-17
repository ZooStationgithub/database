package nl.zoostation.database.model.grid;

import java.lang.annotation.*;

/**
 * @author valentinnastasi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Path {

    String value();

    boolean exactMatch() default true;

    boolean collection() default false;

}
