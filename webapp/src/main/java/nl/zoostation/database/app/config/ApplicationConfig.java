package nl.zoostation.database.app.config;

import nl.zoostation.database.app.config.MvcConfig;
import nl.zoostation.database.app.config.ServiceConfig;
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
