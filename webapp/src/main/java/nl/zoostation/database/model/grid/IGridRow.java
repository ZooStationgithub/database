package nl.zoostation.database.model.grid;

import java.io.Serializable;

/**
 * @author valentinnastasi
 */
public interface IGridRow<K extends Serializable> extends Serializable {

    K getId();

}
