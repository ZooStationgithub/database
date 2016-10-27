package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IGenericDAO;
import nl.zoostation.database.model.domain.PersistentEntity;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
abstract class GenericDAO<E extends PersistentEntity, K extends Serializable> extends SessionAwareDAO implements IGenericDAO<E, K> {

    protected GenericDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E save(E entity) {
        return (E) getSession().merge(entity);
    }

    @Override
    public void delete(E entity) {
        getSession().delete(entity);
    }

    @Override
    public void delete(K id) {
        findOne(id).ifPresent(this::delete);
    }

    @Override
    public Optional<E> findOne(K id) {
        return Optional.ofNullable(getSession().get(getEntityType(), id));
    }


    @Override
    public List<E> findAll() {
        return getSession().createQuery("from " + getEntityType().getSimpleName(), getEntityType()).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findMany(Collection<K> ids) {
        return getSession().createQuery("from " + getEntityType().getSimpleName() + " where id in (:idList)")
                .setParameterList("idList", ids).list();
    }

    protected abstract Class<E> getEntityType();
}
