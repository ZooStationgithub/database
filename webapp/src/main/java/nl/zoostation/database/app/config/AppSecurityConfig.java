package nl.zoostation.database.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author valentinnastasi
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    private final AccessDeniedHandler accessDeniedHandler;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AppSecurityConfig(
            PasswordEncoder passwordEncoder,
            AccessDeniedHandler accessDeniedHandler,
            UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.accessDeniedHandler = accessDeniedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/account/activate").permitAll()
                .antMatchers("/account/**").hasAnyRole("SU", "ADMIN")
                .antMatchers("/developer/edit").hasAnyRole("SU", "ADMIN", "ZS_USER")
                .antMatchers("/developer/delete").hasAnyRole("SU", "ADMIN")
                .antMatchers("/admin/**").hasAnyRole("SU", "ADMIN")
                .antMatchers("/**").authenticated()

                .and()
                .formLogin().loginPage("/login").failureForwardUrl("/login?error").successForwardUrl("/index")

                .and()
                .logout().logoutUrl("/performlogout").logoutSuccessUrl("/login?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)

                .and()
                .rememberMe().key("rememberme").rememberMeParameter("remember-me").tokenValiditySeconds(86400)

                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/bundles/**", "/bundles/**", "/assets/**");
    }
}
