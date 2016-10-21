package nl.zoostation.database.app.config;

import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.mail.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.util.Properties;

/**
 * @author valentinnastasi
 */
@Configuration
@EnableAsync
public class MailConfig {

    @Value("${mail.server.host}") private String mailServerHost;
    @Value("${mail.server.port}") private Integer mailServerPort;
    @Value("${mail.server.credentials.login}") private String mailServerLogin;
    @Value("${mail.server.credentials.password}") private String mailServerPassword;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailServerHost);
        javaMailSender.setPort(mailServerPort);
        javaMailSender.setUsername(mailServerLogin);
        javaMailSender.setPassword(mailServerPassword);
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setProtocol("smtp");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean factoryBean = new FreeMarkerConfigurationFactoryBean();
        factoryBean.setTemplateLoaderPath("classpath:/templates/");
        factoryBean.setDefaultEncoding("UTF-8");
        return factoryBean;
    }

    @Bean
    public IMailService mailService(@Autowired JavaMailSender mailSender, @Autowired freemarker.template.Configuration freeMarkerConfiguration) {
        return new MailService(mailSender, freeMarkerConfiguration);
    }

}
