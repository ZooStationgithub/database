package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.PersistentEntity;
import org.hibernate.SessionFactory;

import java.io.Serializable;

/**
 * @author valentinnastasi
 */
public class SimpleGenericEntityDAO<E extends PersistentEntity, K extends Serializable> extends SimpleGenericReadOnlyEntityDAO<E, K> implements IGenericEntityDAO<E, K> {

    public SimpleGenericEntityDAO(
            SessionFactory sessionFactory,
            Class<E> entityClass,
            Class<K> identifierClass) {

        super(sessionFactory, entityClass, identifierClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E save(@NotNull E entity) {
        logger.debug("Saving entity {}", entity);
        return (E) getSession().merge(entity);
    }

    @Override
    public void delete(@NotNull E entity) {
        logger.debug("Deleting entity {}", entity);
        getSession().delete(entity);
    }

    @Override
    public void delete(@NotNull K id) {
        logger.debug("Deleting entity with ID {}", id);
        E entity = getSession().get(getEntityType(), id);
        getSession().delete(entity);
    }

    @Override
    public E create() {
        try {
            return getEntityType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }



}
