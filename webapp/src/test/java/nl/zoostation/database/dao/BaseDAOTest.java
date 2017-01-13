package nl.zoostation.database.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import nl.zoostation.database.app.config.DaoConfig;
import nl.zoostation.database.app.config.TestDaoConfig;
import nl.zoostation.database.app.config.DatasourceConfig;
import nl.zoostation.database.app.config.PropertiesConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author valentinnastasi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, DatasourceConfig.class, DaoConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection = "dataSource", dataSetLoader = ColumnDetectorXmlDataSetLoader.class)
@Transactional
public abstract class BaseDAOTest {
}
