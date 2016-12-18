package nl.zoostation.database.model.form;

import java.io.Serializable;

/**
 * @author valentinnastasi
 */
public interface IFormObject<K extends Serializable> {

    K getId();

}
