package nl.zoostation.database.app.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author valentinnastasi
 */
@Configuration
@EnableTransactionManagement
public class DatasourceConfig {

    @Value("${database.url}")
    private String url;
    @Value("${database.user}")
    private String user;
    @Value("${database.password}")
    private String password;
    @Value("${database.driver}")
    private String driverClassName;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${database.schema}")
    private String defaultSchema;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        org.hibernate.cfg.Configuration configuration = new LocalSessionFactoryBuilder(dataSource)
                .scanPackages("nl.zoostation.database.model.domain", "nl.zoostation.database.model.view")
                //.setProperty("hibernate.default_schema", defaultSchema)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.dialect", hibernateDialect)
                .setProperty("hibernate.order_updates", "true")
                .setProperty("hibernate.cache.use_second_level_cache", "true")
                .setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        return configuration.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
