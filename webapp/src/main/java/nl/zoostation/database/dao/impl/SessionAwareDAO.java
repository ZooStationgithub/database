package nl.zoostation.database.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author valentinnastasi
 */
abstract class SessionAwareDAO {

    private final SessionFactory sessionFactory;

    protected SessionAwareDAO(SessionFactory sessionFactory) {
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
}
