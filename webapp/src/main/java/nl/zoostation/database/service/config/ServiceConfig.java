package nl.zoostation.database.service.config;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.config.DaoConfig;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.mail.config.MailConfig;
import nl.zoostation.database.service.IAccountService;
import nl.zoostation.database.service.impl.AccountService;
import nl.zoostation.database.service.listeners.TransactionEventListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author valentinnastasi
 */
@Configuration
@Import({DaoConfig.class, MailConfig.class})
public class ServiceConfig {

    @Bean
    public TransactionEventListeners transactionEventListeners() {
        return new TransactionEventListeners();
    }

    @Bean
    public IAccountService accountService(@Autowired IAccountDAO accountDAO, @Autowired PasswordEncoder passwordEncoder, @Autowired IMailService mailService) {
        return new AccountService(accountDAO, passwordEncoder, mailService);
    }

}
