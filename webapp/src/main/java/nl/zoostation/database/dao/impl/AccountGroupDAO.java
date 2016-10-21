package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IAccountGroupDAO;
import nl.zoostation.database.model.domain.AccountGroup;
import org.hibernate.SessionFactory;

/**
 * @author valentinnastasi
 */
public class AccountGroupDAO extends HibernateGenericDAO<AccountGroup, Long> implements IAccountGroupDAO {

    public AccountGroupDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<AccountGroup> getEntityType() {
        return AccountGroup.class;
    }
}
