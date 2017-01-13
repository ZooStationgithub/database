package nl.zoostation.database.service.impl;

import nl.zoostation.database.annotations.validation.NotEmpty;
import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.exception.ObjectDescriptor;
import nl.zoostation.database.exception.ObjectNotFoundException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.service.IAccountManagementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public class AccountManagementService extends TransactionAwareService implements IAccountManagementService {

    private static final Logger logger = LogManager.getLogger(AccountManagementService.class);

    @Value("${mail.server.app.box}")
    private String appMailBox;

    @Value("${mail.account.activation.link}")
    private String accountActivationLink;

    private final IAccountDAO accountDAO;
    private final IMailService mailService;
    private final PasswordEncoder passwordEncoder;

    public AccountManagementService(
            IAccountDAO accountDAO,
            IMailService mailService,
            PasswordEncoder passwordEncoder) {

        this.accountDAO = accountDAO;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> findByLogin(@NotEmpty String login) {
        logger.debug("Fidning account with login '{}'", login);
        return accountDAO.findByLogin(login);
    }

    @Transactional
    @Override
    public void activate(@NotNull Long id) {
        logger.debug("Activating account wth ID {}", id);
        Account account = accountDAO.findOne(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectDescriptor.ofName(Account.class).with("ID", id)));
        if (account.getActivationDate().isPresent()) {
            logger.warn("Account with ID {} is already activated", id);
            return;
        }
        account.setActivationDate(Instant.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDAO.save(account);
    }

    @Transactional(readOnly = true)
    @Override
    public void resendActivation(@NotNull Long id) {
        logger.debug("Resending activation link for account with ID {}", id);
        Account account = accountDAO.findOne(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectDescriptor.ofName(Account.class).with("ID", id)));

        EmailDescription emailDescription = new EmailDescription.Builder()
                .setSubject("Account on zoostation.nl")
                .addTo(account.getLogin())
                .setFrom(appMailBox)
                .setTemplateName("newAccount.ftl")
                .addModelEntry("login", account.getLogin())
                .addModelEntry("password", account.getPassword())
                .addModelEntry("id", account.getId())
                .build();

        doAfterCommit(() -> mailService.sendMailAsync(emailDescription));
    }
}
