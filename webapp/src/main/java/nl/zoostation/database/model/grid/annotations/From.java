package nl.zoostation.database.model.grid.annotations;

import java.lang.annotation.*;

/**
 * @author valentinnastasi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface From {

    String value();

}
