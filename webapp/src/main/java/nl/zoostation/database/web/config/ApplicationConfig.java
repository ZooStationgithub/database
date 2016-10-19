package nl.zoostation.database.web.config;

import nl.zoostation.database.security.config.SecurityConfig;
import nl.zoostation.database.service.config.ServiceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author valentinnastasi
 */
@Configuration
@Import({MvcConfig.class, ServiceConfig.class})
@ImportResource("/WEB-INF/spring-security.xml")
public class ApplicationConfig {
}
