package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.exception.ObjectNotFoundException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.model.domain.AccountGroup;
import nl.zoostation.database.model.domain.SecurityRole;
import nl.zoostation.database.model.form.AccountFormObject;
import nl.zoostation.database.model.form.AccountFormWrapper;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author val
 */
public class AccountFormServiceTest extends BaseServiceTest {

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IGenericEntityDAO<AccountGroup, Long> accountGroupDAO;

    @Autowired
    private IMailService mailService;

    @Autowired
    private AccountFormService accountFormService;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(accountDAO, mailService);
    }

    @Test
    public void testPrepareForm() throws Exception {
        when(accountGroupDAO.findAll()).thenReturn(createAccountGroups());

        AccountFormWrapper formWrapper = accountFormService.prepareForm(Optional.empty());
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isInstanceOf(AccountFormObject.class);
        assertThat(formWrapper.getAccountGroups()).isNotNull().hasSize(1)
                .usingFieldByFieldElementComparator().containsExactly(new AccountGroup(1L, "G", SecurityRole.ROLE_ADMIN));
    }

    @Test
    public void testSave() throws Exception {
        final Long accountId = 1L;
        final String login = "q@q.q";
        final String password = "pass";
        final Long groupId = 2L;

        AccountGroup accountGroup = new AccountGroup(groupId, "G", SecurityRole.ROLE_ADMIN);
        when(accountGroupDAO.findOne(groupId)).thenReturn(Optional.of(accountGroup));
        when(accountDAO.create()).thenReturn(new Account());
        when(accountDAO.save(any(Account.class))).thenReturn(new Account(accountId, login, password, Instant.now(), null));

        accountFormService.save(new AccountFormObject(login, password, groupId));

        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountDAO).save(accountArgumentCaptor.capture());
        Account savingAccount = accountArgumentCaptor.getValue();
        assertThat(savingAccount).isNotNull();
        assertThat(savingAccount.getId()).isNull();
        assertThat(savingAccount.getLogin()).isEqualTo(login);
        assertThat(savingAccount.getPassword()).isEqualTo(password);
        assertThat(savingAccount.getCreationDate()).isNotNull();
        assertThat(savingAccount.getAccountGroup()).isNotNull().isEqualToComparingFieldByField(accountGroup);

        ArgumentCaptor<EmailDescription> emailDescriptionArgumentCaptor = ArgumentCaptor.forClass(EmailDescription.class);
        verify(mailService).sendMailAsync(emailDescriptionArgumentCaptor.capture());
        EmailDescription emailDescription = emailDescriptionArgumentCaptor.getValue();
        assertThat(emailDescription).isNotNull();
        assertThat(emailDescription.getToRecipients()).contains(login);
        assertThat(emailDescription.getModel()).containsEntry("login", login).containsEntry("password", password);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testSaveNoAccountGroupFound() throws Exception {
        final Long groupId = 1L;
        when(accountGroupDAO.findOne(groupId)).thenReturn(Optional.empty());
        when(accountDAO.create()).thenReturn(new Account());

        accountFormService.save(new AccountFormObject("", "", groupId));
    }

    private List<AccountGroup> createAccountGroups() {
        return Arrays.asList(new AccountGroup(1L, "G", SecurityRole.ROLE_ADMIN), new AccountGroup(2L, "F", SecurityRole.ROLE_SU));
    }

}