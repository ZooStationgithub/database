package nl.zoostation.database.app.config;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.IAccountGroupDAO;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.service.IAccountService;
import nl.zoostation.database.service.impl.AccountService;
import nl.zoostation.database.service.listeners.TransactionEventListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    public IAccountService accountService(
            @Autowired IAccountDAO accountDAO,
            @Autowired IAccountGroupDAO accountGroupDAO,
            @Autowired PasswordEncoder passwordEncoder,
            @Autowired IMailService mailService) {
        return new AccountService(accountDAO, accountGroupDAO, passwordEncoder, mailService);
    }

}
