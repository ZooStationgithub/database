package nl.zoostation.database.app.config;

import nl.zoostation.database.service.IAccountManagementService;
import nl.zoostation.database.web.security.SecurityUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author valentinnastasi
 */
@Configuration
public class TestSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(IAccountManagementService accountManagementService) {
        return new SecurityUserDetailsService(accountManagementService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
