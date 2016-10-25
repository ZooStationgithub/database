package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.model.domain.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
@Repository
public class AccountDAO extends GenericDAO<Account, Long> implements IAccountDAO {

    @Autowired
    public AccountDAO(SessionFactory sessionFactory) {
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

    @Override
    public void activate(String login) {
        Optional<Account> account = findByLogin(login);
        if (!account.isPresent()) {
            throw new IllegalStateException("Account with login '" + login + "' doesn't exist");
        }
        if (account.get().getActivationDate().isPresent()) {
            throw new IllegalStateException("Account with login '" + login + "' is already activated");
        }

        Account accountUnwrapped = account.get();
        accountUnwrapped.setActivationDate(Instant.now());
        save(accountUnwrapped);
    }
}
