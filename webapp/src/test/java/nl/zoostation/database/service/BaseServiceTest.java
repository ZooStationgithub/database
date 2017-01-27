package nl.zoostation.database.service;

import nl.zoostation.database.app.config.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author val
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, IntegrationConfig.class, TestDaoConfig.class, TestMailConfig.class, AuthenticationConfig.class, ServiceConfig.class})
public abstract class BaseServiceTest {
}
