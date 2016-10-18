package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.model.domain.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
@Repository("accountDAO")
public class AccountDAO extends HibernateGenericDAO<Account, Long> implements IAccountDAO {

    public AccountDAO(@Autowired SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Account> getEntityType() {
        return Account.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<Account> findByLogin(String login) {
        return getSession().createQuery("from Account where login = :login").setParameter("login", login).uniqueResultOptional();
    }
}
