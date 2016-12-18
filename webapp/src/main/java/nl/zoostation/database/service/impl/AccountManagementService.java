package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.service.IAccountManagementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public class AccountManagementService extends TransactionAwareService implements IAccountManagementService {

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
    public Optional<Account> findByLogin(String login) {
        Objects.requireNonNull(login, "Parameter 'login' is required");
        return accountDAO.findByLogin(login);
    }

    @Transactional
    @Override
    public void activate(Long id) {
        Objects.requireNonNull(id, "Parameter 'id' is required");
        Account account = accountDAO.findOne(id).orElseThrow(() -> new IllegalStateException("Account with ID '" + id + "' doesn't exist"));
        if (account.getActivationDate().isPresent()) {
            throw new IllegalStateException("Account with ID '" + id + "' is already activated");
        }
        account.setActivationDate(Instant.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountDAO.save(account);
    }

    @Transactional(readOnly = true)
    @Override
    public void resendActivation(Long id) {
        Objects.requireNonNull(id, "Parameter 'id' is required");
        Account account = accountDAO.findOne(id)
                .orElseThrow(() -> new IllegalStateException("Account with ID " + id + " not found"));

        EmailDescription emailDescription = new EmailDescription.Builder()
                .setSubject("Account on zoostation.nl")
                .addTo(account.getLogin())
                .setFrom(appMailBox)
                .setTemplateName("newAccount.ftl")
                .addModelEntry("login", account.getLogin())
                .addModelEntry("password", account.getPassword())
                .addModelEntry("id", account.getId())
                .build();

        doAfterCommit(() -> mailService.sendMail(emailDescription));
    }
}
