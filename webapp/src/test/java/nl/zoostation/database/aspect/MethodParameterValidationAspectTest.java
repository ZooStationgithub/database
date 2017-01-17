package nl.zoostation.database.aspect;

import nl.zoostation.database.app.config.AopConfig;
import nl.zoostation.database.app.config.DaoConfig;
import nl.zoostation.database.app.config.DatasourceConfig;
import nl.zoostation.database.app.config.PropertiesConfig;
import nl.zoostation.database.dao.impl.AccountDAO;
import nl.zoostation.database.exception.ErrorMessage;
import nl.zoostation.database.exception.InvalidParameterException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author valentinnastasi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesConfig.class, DatasourceConfig.class, DaoConfig.class, AopConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional
public class MethodParameterValidationAspectTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void testNotNullParameter() throws Exception {
        accountDAO.findOne(1L);
    }

    @Test
    public void testNullParameter() throws Exception {
        assertThatThrownBy(() -> accountDAO.findOne(null))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining(ErrorMessage.NULL_PARAMETER.getCode());
    }

    @Test
    public void testNotEmptyStringParameter() throws Exception {
        accountDAO.findByLogin("not empty");
    }

    @Test
    public void testEmptyStringParameter() throws Exception {
        assertThatThrownBy(() -> accountDAO.findByLogin(""))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_STRING_PARAMETER.getCode());
    }

    @Test
    public void testNullStringParameter() throws Exception {
        assertThatThrownBy(() -> accountDAO.findByLogin(""))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_STRING_PARAMETER.getCode());
    }

    @Test
    public void testNotEmptyCollectionParameter() throws Exception {
        accountDAO.findMany(Collections.singletonList(1L));
    }

    @Test
    public void testEmptyCollectionParameter() throws Exception {
        assertThatThrownBy(() -> accountDAO.findMany(Collections.emptyList()))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_COLLECTION_PARAMETER.getCode());
    }

    @Test
    public void testNullCollectionParameter() throws Exception {
        assertThatThrownBy(() -> accountDAO.findMany(Collections.emptyList()))
                .isInstanceOf(InvalidParameterException.class)
                .hasMessageContaining(ErrorMessage.EMPTY_COLLECTION_PARAMETER.getCode());
    }
}