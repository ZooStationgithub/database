package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.service.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public class AccountService extends TransactionAwareService implements IAccountService {

    private static final Logger logger = LogManager.getLogger(AccountService.class);

    private final IAccountDAO accountDAO;
    private final PasswordEncoder passwordEncoder;
    private final IMailService mailService;

    public AccountService(IAccountDAO accountDAO, PasswordEncoder passwordEncoder, IMailService mailService) {
        this.accountDAO = accountDAO;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Transactional
    @Override
    public void create(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDAO.save(account);
    }

    @Transactional
    @Override
    public void activate(String login) {
        logger.debug("Activating account with login '{}'", login);
        Objects.requireNonNull(login, "Parameter 'login' is required");
        accountDAO.activate(login);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> findByLogin(String login) {
        logger.debug("Finding account for login '{}'", login);
        Objects.requireNonNull(login, "Parameter 'login' is required");
        return accountDAO.findByLogin(login);
    }

}
