package nl.zoostation.database.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.domain.Framework;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author val
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
@DatabaseSetup({"/datasets/common/programming_languages.xml", "/datasets/common/frameworks.xml"})
public class SimpleGenericEntityDAOTest extends BaseDAOTest {

    @Autowired
    private SimpleGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;

    @Autowired
    private SimpleGenericEntityDAO<Framework, Long> frameworkDAO;

    @Test
    public void testCreate() throws Exception {
        Framework framework = frameworkDAO.create();
        assertThat(framework).isNotNull();
    }

    @Test
    @ExpectedDatabase(value = "/datasets/SimpleGenericEntityDAOTest/saveInsert_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveInsert() throws Exception {
        Framework framework = new Framework();
        framework.setName("Alibaba");
        framework.setProgrammingLanguage(programmingLanguageDAO.findOne(1001L).get());
        Framework savedFramework = frameworkDAO.save(framework);
        frameworkDAO.flush();
        assertThat(savedFramework).isNotNull();
        assertThat(savedFramework.getId()).isNotNull();
    }

    @Test
    @ExpectedDatabase(value = "/datasets/SimpleGenericEntityDAOTest/saveUpdate_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testSaveUpdate() throws Exception {
        Framework framework = new Framework();
        framework.setId(1005L);
        framework.setName("TRALALA");
        framework.setProgrammingLanguage(programmingLanguageDAO.findOne(1003L).get());
        frameworkDAO.save(framework);
        frameworkDAO.flush();
    }

    @Test
    @ExpectedDatabase(value = "/datasets/SimpleGenericEntityDAOTest/delete_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDelete() throws Exception {
        Framework framework = new Framework();
        framework.setId(1005L);
        frameworkDAO.delete(framework);
        frameworkDAO.flush();
    }

    @Test
    @ExpectedDatabase(value = "/datasets/SimpleGenericEntityDAOTest/delete_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testDeleteById() throws Exception {
        frameworkDAO.delete(1005L);
        frameworkDAO.flush();
    }

    @Test
    public void testFindOne() throws Exception {
        Optional<Framework> frameworkOptional = frameworkDAO.findOne(1005L);
        assertThat(frameworkOptional).isNotEmpty();
        Framework framework = frameworkOptional.get();
        assertThat(framework.getId()).isEqualTo(1005L);
        assertThat(framework.getName()).isEqualTo("Hibernate");
        assertThat(framework.getProgrammingLanguage()).isNotNull();
        assertThat(framework.getProgrammingLanguage().getId()).isEqualTo(1000L);
    }

    @Test
    public void testFindMany() throws Exception {
        List<Framework> list = frameworkDAO.findMany(Arrays.asList(1000L, 1001L, 1100L));
        assertThat(list).isNotNull().hasSize(2)
                .contains(new Framework(1000L, "Spring"), new Framework(1001L, "AngularJS"));
    }

    @Test
    public void testFindAll() throws Exception {
        List<Framework> list = frameworkDAO.findAll();
        assertThat(list).isNotNull().hasSize(6).contains(new Framework(1000L, "Spring"), new Framework(1001L, "AngularJS"),
                new Framework(1002L, "Qt"), new Framework(1003L, "jQuery"), new Framework(1004L, "NodeJS"),
                new Framework(1005L, "Hibernate"));
    }
}