package nl.zoostation.database.app.config;

import nl.zoostation.database.service.listeners.TransactionEventListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author valentinnastasi
 */
@Configuration
@Import({DaoConfig.class, MailConfig.class})
@ComponentScan("nl.zoostation.database.service.impl")
public class ServiceConfig {

    @Bean
    public TransactionEventListeners transactionEventListeners() {
        return new TransactionEventListeners();
    }

}
