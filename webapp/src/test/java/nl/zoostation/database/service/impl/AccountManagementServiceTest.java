package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.exception.ObjectNotFoundException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author val
 */
public class AccountManagementServiceTest extends BaseServiceTest {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";
    private static final long ID = 1L;
    private static final Instant CREATION_DATE = Instant.now();

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IMailService mailService;

    @Autowired
    private AccountManagementService accountManagementService;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(accountDAO, mailService);
    }

    @Test
    public void testFindByLogin() throws Exception {
        Account account = new Account();
        when(accountDAO.findByLogin(LOGIN)).thenReturn(Optional.of(account));
        Optional<Account> accountOptional = accountManagementService.findByLogin(LOGIN);
        assertThat(accountOptional).contains(account);
    }

    @Test
    public void testFindByLoginNotFund() throws Exception {
        when(accountDAO.findByLogin(LOGIN)).thenReturn(Optional.empty());
        Optional<Account> accountOptional = accountManagementService.findByLogin(LOGIN);
        assertThat(accountOptional).isEmpty();
    }

    @Test
    public void testActivate() throws Exception {
        Account account = new Account();
        account.setId(ID);
        account.setLogin(LOGIN);
        account.setPassword(PASSWORD);
        account.setCreationDate(CREATION_DATE);
        when(accountDAO.findOne(ID)).thenReturn(Optional.of(account));

        accountManagementService.activate(ID);
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountDAO).save(accountArgumentCaptor.capture());

        Account savedAccount = accountArgumentCaptor.getValue();
        assertThat(savedAccount).isNotNull();
        assertThat(savedAccount.getId()).isEqualTo(ID);
        assertThat(savedAccount.getLogin()).isEqualTo(LOGIN);
        assertThat(savedAccount.getPassword()).isNotNull().isNotEqualTo(PASSWORD);
        assertThat(savedAccount.getCreationDate()).isEqualTo(CREATION_DATE);
        assertThat(savedAccount.getActivationDate()).isNotEmpty();
    }

    @Test
    public void testActivateAlreadyActivated() throws Exception {
        Account account = new Account();
        account.setId(ID);
        account.setLogin(LOGIN);
        account.setPassword(PASSWORD);
        account.setCreationDate(CREATION_DATE);
        account.setActivationDate(Instant.now());
        when(accountDAO.findOne(ID)).thenReturn(Optional.of(account));

        accountManagementService.activate(ID);
        verify(accountDAO, times(0)).save(any(Account.class));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testActivateAccountNotFound() throws Exception {
        accountManagementService.activate(-1L);
    }

    @Test
    public void testResendActivation() throws Exception {
        Account account = new Account();
        account.setId(ID);
        account.setLogin(LOGIN);
        account.setPassword(PASSWORD);
        account.setCreationDate(CREATION_DATE);
        account.setActivationDate(Instant.now());
        when(accountDAO.findOne(ID)).thenReturn(Optional.of(account));

        accountManagementService.resendActivation(ID);
        ArgumentCaptor<EmailDescription> argumentCaptor = ArgumentCaptor.forClass(EmailDescription.class);
        verify(mailService).sendMailAsync(argumentCaptor.capture());

        EmailDescription emailDescription = argumentCaptor.getAllValues().get(0);
        assertThat(emailDescription).isNotNull();
        assertThat(emailDescription.getToRecipients()).contains(LOGIN);
        assertThat(emailDescription.getModel()).containsEntry("id", ID).containsEntry("login", LOGIN).containsEntry("password", PASSWORD);
    }
}