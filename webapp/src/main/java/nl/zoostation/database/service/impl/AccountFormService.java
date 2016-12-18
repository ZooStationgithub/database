package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.model.domain.AccountGroup;
import nl.zoostation.database.model.domain.SecurityRole;
import nl.zoostation.database.model.form.AccountFormObject;
import nl.zoostation.database.model.form.AccountFormWrapper;
import nl.zoostation.database.service.IAccountFormService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author valentinnastasi
 */
public class AccountFormService extends AbstractFormService<Account, Long, AccountFormObject, AccountFormWrapper>
        implements IAccountFormService {

    @Value("${mail.server.app.box}")
    private String appMailBox;

    @Value("${mail.account.activation.link}")
    private String accountActivationLink;

    private final IGenericEntityDAO<AccountGroup, Long> accountGroupDAO;
    private final IMailService mailService;

    public AccountFormService(
            IGenericEntityDAO<Account, Long> accountDAO,
            IGenericEntityDAO<AccountGroup, Long> accountGroupDAO,
            IMailService mailService) {
        super(accountDAO);
        this.accountGroupDAO = accountGroupDAO;
        this.mailService = mailService;
    }

    @Transactional(readOnly = true)
    @Override
    public AccountFormWrapper prepareForm(Optional<Long> identifier) {
        AccountFormObject formObject = new AccountFormObject();
        AccountFormWrapper formWrapper = new AccountFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setAccountGroups(accountGroupDAO.findAll().stream().filter(g -> g.getSecurityRole() != SecurityRole.ROLE_SU ).collect(toList()));
        return formWrapper;
    }

    @Transactional
    @Override
    public Account save(AccountFormObject formObject) {
        Account account = super.save(formObject);
        EmailDescription emailDescription = new EmailDescription.Builder()
                .setSubject("Account on zoostation.nl")
                .setFrom(appMailBox)
                .addTo(formObject.getEmail())
                .setTemplateName("newAccount.ftl")
                .addModelEntry("login", formObject.getEmail())
                .addModelEntry("password", formObject.getPassword())
                .addModelEntry("link", MessageFormat.format(accountActivationLink, account.getId()))
                .build();

        doAfterCommit(() -> mailService.sendMailAsync(emailDescription));

        return account;
    }

    @Override
    protected void entityToForm(Account entity, AccountFormObject formObject) {
    }

    @Override
    protected void formToEntity(AccountFormObject formObject, Account entity) {
        entity.setLogin(formObject.getEmail());
        entity.setPassword(formObject.getPassword());
        entity.setCreationDate(Instant.now());
        entity.setActivationDate(null);
        AccountGroup accountGroup = accountGroupDAO.findOne(formObject.getGroupId())
                .orElseThrow(() -> new IllegalStateException("Account group with ID " + formObject.getId() + " was not found"));
        entity.setAccountGroup(accountGroup);
    }

}
