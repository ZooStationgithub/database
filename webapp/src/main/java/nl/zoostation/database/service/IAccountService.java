package nl.zoostation.database.service;

import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.model.domain.AccountGroup;
import nl.zoostation.database.model.form.AccountForm;

import java.util.List;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IAccountService {

    void create(Account account);

    void create(AccountForm accountForm);

    void activate(String login);

    void activate(Long id);

    List<AccountGroup> findGroups();

    Optional<Account> findByLogin(String login);

    void resendActivation(Long id);

}
