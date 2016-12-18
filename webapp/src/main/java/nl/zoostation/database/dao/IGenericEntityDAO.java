package nl.zoostation.database.dao;

import nl.zoostation.database.model.domain.PersistentEntity;

import java.io.Serializable;

/**
 * @author valentinnastasi
 */
public interface IGenericEntityDAO<E extends PersistentEntity, K extends Serializable> extends IGenericReadOnlyEntityDAO<E, K> {

    E save(E entity);

    void delete(E entity);

    void delete(K id);

    E create();

}
