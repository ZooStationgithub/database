package nl.zoostation.database.dao;

import nl.zoostation.database.model.domain.Account;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IAccountDAO extends IGenericDAO<Account, Long> {

    Optional<Account> findByLogin(String login);

    void activate(String login);

}
