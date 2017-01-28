package nl.zoostation.database.web.security;

import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.model.domain.AccountGroup;
import nl.zoostation.database.model.domain.SecurityRole;
import nl.zoostation.database.service.IAccountManagementService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.Instant;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

/**
 * @author valentinnastasi
 */
public class AuthenticationTest extends BaseSecurityTest {

    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Autowired
    @Qualifier("accountManagementService")
    private IAccountManagementService mockAccountManagementService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testLoginUnAuthenticated() throws Exception {
        when(mockAccountManagementService.findByLogin(USER)).thenReturn(Optional.empty());

        getMockMvc().perform(formLogin().user(USER).password(PASSWORD))
                .andExpect(unauthenticated());
    }

    @Test
    public void testLogin() throws Exception {
        AccountGroup group = new AccountGroup(1L, "group", SecurityRole.ROLE_ADMIN);
        Account account = new Account(1L, USER, passwordEncoder.encode(PASSWORD), Instant.now(), Instant.now(), group);
        when(mockAccountManagementService.findByLogin(USER)).thenReturn(Optional.of(account));

        getMockMvc().perform(formLogin().user(USER).password(PASSWORD))
                .andExpect(authenticated().withUsername(USER).withRoles("ADMIN"));
    }

    @Test
    @WithMockUser(username = USER, password = PASSWORD, roles = "ADMIN")
    public void testLogout() throws Exception {
        getMockMvc().perform(logout()).andExpect(unauthenticated());
    }

}
