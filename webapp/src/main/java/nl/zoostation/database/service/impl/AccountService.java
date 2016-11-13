package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.IAccountGroupDAO;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.model.domain.AccountGroup;
import nl.zoostation.database.model.domain.SecurityRole;
import nl.zoostation.database.model.form.AccountForm;
import nl.zoostation.database.service.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.ServletContext;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author valentinnastasi
 */
@Service
public class AccountService extends TransactionAwareService implements IAccountService {

    private static final Logger logger = LogManager.getLogger(AccountService.class);

    @Value("${mail.server.app.box}") private String appMailBox;
    @Value("${mail.account.activation.link}") private String accountActivationLink;

    private final IAccountDAO accountDAO;
    private final IAccountGroupDAO accountGroupDAO;
    private final PasswordEncoder passwordEncoder;
    private final IMailService mailService;

    @Autowired
    public AccountService(
            IAccountDAO accountDAO,
            IAccountGroupDAO accountGroupDAO,
            PasswordEncoder passwordEncoder,
            IMailService mailService) {
        this.accountDAO = accountDAO;
        this.accountGroupDAO = accountGroupDAO;
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
    public void create(AccountForm accountForm) {
        logger.debug("Creating new account for form object {}", accountForm);
        Objects.requireNonNull(accountForm, "Form data should not be empty");

        Account account = new Account(accountForm.getEmail(), accountForm.getPassword(), Instant.now(), null);
        Optional<AccountGroup> accountGroup = accountGroupDAO.findOne(accountForm.getGroupId());
        account.setAccountGroup(accountGroup.orElseThrow(() -> new IllegalArgumentException("Group with id " + accountForm.getGroupId() + " does not exist")));
        account = accountDAO.save(account);

        EmailDescription emailDescription = new EmailDescription.Builder()
                .setSubject("Account on zoostation.nl")
                .setFrom(appMailBox)
                .addTo(accountForm.getEmail())
                .setTemplateName("newAccount.ftl")
                .addModelEntry("login", accountForm.getEmail())
                .addModelEntry("password", accountForm.getPassword())
                .addModelEntry("link", MessageFormat.format(accountActivationLink, account.getId()))
                .build();

        doAfterCommit(() -> mailService.sendMailAsync(emailDescription));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        accountDAO.delete(id);
    }

    @Transactional
    @Override
    public void activate(String login) {
        logger.debug("Activating account with login '{}'", login);
        Objects.requireNonNull(login, "Parameter 'login' is required");
        accountDAO.activate(login);
    }

    @Transactional
    @Override
    public void activate(Long id) {
        logger.debug("Activating account with id {}", id);
        Objects.requireNonNull(id, "Parameter 'id' is required");
        Optional<Account> account = accountDAO.findOne(id);
        if (!account.isPresent()) {
            throw new IllegalStateException("Account with id " + id + " does not exist");
        }
        Account accountUnwrapped = account.get();
        accountUnwrapped.setPassword(passwordEncoder.encode(accountUnwrapped.getPassword()));
        accountUnwrapped.setActivationDate(Instant.now());
        accountDAO.save(accountUnwrapped);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AccountGroup> findGroups() {
        logger.debug("Finding all available account groups");
        return accountGroupDAO.findAll().stream().filter(g -> SecurityRole.ROLE_SU != g.getSecurityRole()).collect(toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Account> findByLogin(String login) {
        logger.debug("Finding account for login '{}'", login);
        Objects.requireNonNull(login, "Parameter 'login' is required");
        return accountDAO.findByLogin(login);
    }

    @Transactional(readOnly = true)
    @Override
    public void resendActivation(Long id) {
        logger.debug("Resending activation link for account with id " + id);
        Objects.requireNonNull(id, "Parameter 'login' is required");
        Optional<Account> account = accountDAO.findOne(id);
        if (!account.isPresent()) {
            throw new IllegalStateException("Account with id " + id + " does not exist");
        }

        Account accountUnwrapped = account.get();
        EmailDescription emailDescription = new EmailDescription.Builder()
                .setSubject("Account on zoostation.nl")
                .addTo(accountUnwrapped.getLogin())
                .setFrom(appMailBox)
                .setTemplateName("newAccount.ftl")
                .addModelEntry("login", accountUnwrapped.getLogin())
                .addModelEntry("password", accountUnwrapped.getPassword())
                .addModelEntry("id", accountUnwrapped.getId())
                .build();

        doAfterCommit(() -> mailService.sendMail(emailDescription));
    }

}
