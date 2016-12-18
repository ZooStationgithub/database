package nl.zoostation.database.service;

import nl.zoostation.database.model.domain.Account;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IAccountManagementService {

    Optional<Account> findByLogin(String login);

    void activate(Long id);

    void resendActivation(Long id);

}
