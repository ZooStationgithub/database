package nl.zoostation.database.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author valentinnastasi
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({MvcConfig.class, ServiceConfig.class})
@ImportResource("/WEB-INF/spring-security.xml")
public class ApplicationConfig {
}
