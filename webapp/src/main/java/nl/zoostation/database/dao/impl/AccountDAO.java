package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.model.domain.Account;
import org.hibernate.SessionFactory;

import java.util.Optional;

import static nl.zoostation.database.app.Constants.Parameters.LOGIN;

/**
 * @author valentinnastasi
 */
public class AccountDAO extends SimpleGenericEntityDAO<Account, Long> implements IAccountDAO {

    private static final String QUERY_BY_LOGIN =
            "from {0} where login = :{1}";

    public AccountDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Account.class, Long.class);
    }

    @Override
    public Optional<Account> findByLogin(String login) {
        return getSession().createQuery(interpolate(QUERY_BY_LOGIN, Account.class.getSimpleName(), LOGIN), Account.class)
                .setParameter(LOGIN, login).uniqueResultOptional();
    }
}
