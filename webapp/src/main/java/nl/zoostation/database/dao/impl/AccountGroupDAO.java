package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IAccountGroupDAO;
import nl.zoostation.database.model.domain.AccountGroup;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class AccountGroupDAO extends GenericDAO<AccountGroup, Long> implements IAccountGroupDAO {

    @Autowired
    public AccountGroupDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<AccountGroup> getEntityType() {
        return AccountGroup.class;
    }
}
