package nl.zoostation.database.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author valentinnastasi
 */
@Configuration
@Import(DatasourceConfig.class)
@ComponentScan("nl.zoostation.database.dao.impl")
public class DaoConfig {

}
