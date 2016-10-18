package nl.zoostation.database.dao;

import nl.zoostation.database.model.domain.PersistentEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IGenericDAO<E extends PersistentEntity, K extends Serializable> {

    E save(E entity);

    void delete(E entity);

    void delete(K id);

    Optional<E> findOne(K id);

    List<E> findAll();

}
