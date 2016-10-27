package nl.zoostation.database.security;

import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.service.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public class SecurityUserDetailsService implements UserDetailsService {

    private static final Logger logger = LogManager.getLogger(SecurityUserDetailsService.class);

    private final IAccountService accountService;

    public SecurityUserDetailsService(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        logger.debug("Loading user credentials for login '{}'", login);

        Optional<Account> account = accountService.findByLogin(login);
        if (account.isPresent()) {
            Account accountUnwrapped = account.get();
            return new org.springframework.security.core.userdetails.User(
                    accountUnwrapped.getLogin(),
                    accountUnwrapped.getPassword(),
                    accountUnwrapped.getActivationDate().isPresent(),
                    true,
                    true,
                    true,
                    extractRoles(accountUnwrapped));
        }
        throw new UsernameNotFoundException("Account with login '" + login + "' not found");
    }

    private Collection<GrantedAuthority> extractRoles(Account account) {
        return Collections.singletonList(new SimpleGrantedAuthority(account.getAccountGroup().getSecurityRole().name()));
    }

}
