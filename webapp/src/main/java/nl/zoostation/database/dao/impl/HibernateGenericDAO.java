package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IGenericDAO;
import nl.zoostation.database.model.domain.PersistentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public abstract class HibernateGenericDAO<E extends PersistentEntity, K extends Serializable> implements IGenericDAO<E, K> {

    private final SessionFactory sessionFactory;

    public HibernateGenericDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected void flush() {
        sessionFactory.getCurrentSession().flush();
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

    protected abstract Class<E> getEntityType();
}
