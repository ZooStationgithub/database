package nl.zoostation.database.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author valentinnastasi
 * @created 14/10/2016 09:51
 */
@Configuration
@Import({MvcConfig.class})
@ImportResource({"/WEB-INF/spring-security.xml"})
public class ApplicationConfig {
}
