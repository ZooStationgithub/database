package nl.zoostation.database.service;

import nl.zoostation.database.model.domain.Account;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IAccountService {

    void create(Account account);

    void activate(String login);

    Optional<Account> findByLogin(String login);

}
