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

    @Value("${database.url}") private String url;
    @Value("${database.user}") private String user;
    @Value("${database.password}") private String password;
    @Value("${database.driver}") private String driverClassName;
    @Value("${database.schema}") private String defaultSchema;
    @Value("${hibernate.dialect}") private String hibernateDialect;
    @Value("${hibernate.show_sq:true}") private String showSql;
    @Value("${hibernate.order_updates:true}") private String orderUpdates;
    @Value("${hibernate.cache.use_second_level_cache:true}") private String useSecondLevelCache;
    @Value("${hibernate.hbm2ddl.auto:validate}") private String hbm2ddl;
    @Value("${hibernate.cache.region.factory_class:rg.hibernate.cache.ehcache.EhCacheRegionFactory}") private String cacheFactoryClass;

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
                .setProperty("hibernate.dialect", hibernateDialect)
                .setProperty("hibernate.show_sql", showSql)
                .setProperty("hibernate.order_updates", orderUpdates)
                .setProperty("hibernate.cache.use_second_level_cache", useSecondLevelCache)
                .setProperty("hibernate.hbm2ddl.auto", hbm2ddl)
                .setProperty("hibernate.cache.region.factory_class", cacheFactoryClass);
        return configuration.buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
