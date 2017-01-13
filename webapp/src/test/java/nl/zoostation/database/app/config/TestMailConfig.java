package nl.zoostation.database.app.config;

import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.mail.impl.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * @author valentinnastasi
 */
@Configuration
public class TestMailConfig {

    @Bean
    public IMailService mailService() {
        return mock(MailService.class);
    }

}
