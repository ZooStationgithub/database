package nl.zoostation.database.dao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author valentinnastasi
 * @created 18/10/2016 10:51
 */
@Configuration
@Import(DatasourceConfig.class)
@ComponentScan("nl.zoostation.database.dao.impl")
public class DaoConfig {
}
