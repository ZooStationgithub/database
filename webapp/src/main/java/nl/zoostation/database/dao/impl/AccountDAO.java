package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotEmpty;
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
    public Optional<Account> findByLogin(@NotEmpty String login) {
        logger.debug("Finding by login {}", login);
        Optional<Account> account = getSession().createQuery(interpolate(QUERY_BY_LOGIN, Account.class.getSimpleName(), LOGIN), Account.class)
                .setParameter(LOGIN, login).uniqueResultOptional();
        logger.trace("Account found: {}", account);
        return account;
    }
}
