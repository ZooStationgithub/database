package nl.zoostation.database.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.zoostation.database.web.security.SecurityUserDetailsService;
import nl.zoostation.database.service.IAccountManagementService;
import nl.zoostation.database.web.security.ZooStationAccessDeniedHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author valentinnastasi
 */
@Configuration
@Import(ServiceConfig.class)
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(IAccountManagementService accountManagementService) {
        return new SecurityUserDetailsService(accountManagementService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(
            ObjectMapper objectMapper,
            MessageSource messageSource) {
        return new ZooStationAccessDeniedHandler(objectMapper, messageSource);
    }

}
