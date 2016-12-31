package nl.zoostation.database.dao.impl;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
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
@DataSet(locations = "/datasets/existing_pl.xml", tearDownOperation = DBOperation.DELETE)
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

    @ExpectedDataSet(locations = "/datasets/one_framework.xml", columnsToIgnore = "id")
    @Test
    public void testSaveInsert() throws Exception {
        Framework framework = new Framework();
        framework.setName("Alibaba");
        framework.setProgrammingLanguage(programmingLanguageDAO.findOne(1001L).get());
        Framework savedFramework = frameworkDAO.save(framework);
        frameworkDAO.flush();
        assertThat(savedFramework).isNotNull();
        assertThat(savedFramework.getId()).isNotNull();
    }

    @DataSet(locations = "/datasets/one_framework.xml")
    @ExpectedDataSet(locations = "/datasets/updated_framework.xml")
    @Test
    public void testSaveUpdate() throws Exception {
        Framework framework = new Framework();
        framework.setId(1100L);
        framework.setName("TRALALA");
        framework.setProgrammingLanguage(programmingLanguageDAO.findOne(1000L).get());
        frameworkDAO.save(framework);
        frameworkDAO.flush();
    }

    @DataSet(locations = {"/datasets/existing_frameworks.xml", "/datasets/one_framework.xml"})
    @ExpectedDataSet(locations = "/datasets/existing_frameworks.xml")
    @Test
    public void testDelete() throws Exception {
        Framework framework = new Framework();
        framework.setId(1100L);
        frameworkDAO.delete(framework);
        frameworkDAO.flush();
    }

    @DataSet(locations = {"/datasets/existing_frameworks.xml", "/datasets/one_framework.xml"})
    @ExpectedDataSet(locations = "/datasets/existing_frameworks.xml")
    @Test
    public void testDeleteById() throws Exception {
        frameworkDAO.delete(1100L);
        frameworkDAO.flush();
    }

    @DataSet(locations = {"/datasets/existing_frameworks.xml", "/datasets/one_framework.xml"})
    @Test
    public void testFindOne() throws Exception {
        Optional<Framework> frameworkOptional = frameworkDAO.findOne(1100L);
        assertThat(frameworkOptional).isNotEmpty();
        Framework framework = frameworkOptional.get();
        assertThat(framework.getId()).isEqualTo(1100L);
        assertThat(framework.getName()).isEqualTo("Alibaba");
        assertThat(framework.getProgrammingLanguage()).isNotNull();
        assertThat(framework.getProgrammingLanguage().getId()).isEqualTo(1001L);
    }

    @DataSet(locations = "/datasets/existing_frameworks.xml")
    @Test
    public void testFindMany() throws Exception {
        List<Framework> list = frameworkDAO.findMany(Arrays.asList(1000L, 1001L, 1100L));
        assertThat(list).isNotNull().hasSize(2)
                .contains(new Framework(1000L, "Spring"), new Framework(1001L, "AngularJS"));
    }

    @DataSet(locations = "/datasets/existing_frameworks.xml")
    @Test
    public void testFindAll() throws Exception {
        List<Framework> list = frameworkDAO.findAll();
        assertThat(list).isNotNull().hasSize(6).contains(new Framework(1000L, "Spring"), new Framework(1001L, "AngularJS"),
                new Framework(1002L, "Qt"), new Framework(1003L, "jQuery"), new Framework(1004L, "NodeJS"),
                new Framework(1005L, "Hibernate"));
    }
}