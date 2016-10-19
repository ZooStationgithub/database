package nl.zoostation.database.dao.config;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.IProfileDAO;
import nl.zoostation.database.dao.impl.AccountDAO;
import nl.zoostation.database.dao.impl.ProfileDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author valentinnastasi
 */
@Configuration
@Import(DatasourceConfig.class)
public class DaoConfig {

    @Bean
    public IAccountDAO accountDAO(@Autowired SessionFactory sessionFactory) {
        return new AccountDAO(sessionFactory);
    }

    @Bean
    public IProfileDAO profileDAO(@Autowired SessionFactory sessionFactory) {
        return new ProfileDAO(sessionFactory);
    }

}
