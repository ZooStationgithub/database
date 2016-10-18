package nl.zoostation.database.service.config;

import nl.zoostation.database.dao.config.DaoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author valentinnastasi
 */
@Configuration
@Import(DaoConfig.class)
@ComponentScan("nl.zoostation.database.service.impl")
public class ServiceConfig {
}
