package nl.zoostation.database.app.config;

import nl.zoostation.database.app.config.DatasourceConfig;
import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.IAccountGroupDAO;
import nl.zoostation.database.dao.IProfileDAO;
import nl.zoostation.database.dao.impl.AccountDAO;
import nl.zoostation.database.dao.impl.AccountGroupDAO;
import nl.zoostation.database.dao.impl.ProfileDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public IAccountGroupDAO accountGroupDAO(@Autowired SessionFactory sessionFactory) {
        return new AccountGroupDAO(sessionFactory);
    }

}
