package nl.zoostation.database.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IGenericReadOnlyEntityDAO<E, K extends Serializable> {

    Optional<E> findOne(K id);

    List<E> findAll();

    List<E> findMany(Collection<K> ids);

}
