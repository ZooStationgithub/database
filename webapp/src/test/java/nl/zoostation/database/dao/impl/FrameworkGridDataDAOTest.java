package nl.zoostation.database.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

/**
 * @author val
 */
@DatabaseSetup({"/datasets/common/programming_languages.xml", "/datasets/common/frameworks.xml"})
public class FrameworkGridDataDAOTest extends BaseDAOTest {

    @Autowired
    private FrameworkGridDataDAO frameworkGridDataDAO;

    @Test
    public void testGetRows() throws Exception {
        List<FrameworkGridRow> rows = frameworkGridDataDAO.getRows(new GridViewInputSpec());
        assertThat(rows).isNotNull().hasSize(6).extracting("name", "programmingLanguageName")
                .contains(tuple("Spring", "Java"), tuple("AngularJS", "JavaScript"), tuple("Qt", "C++"), tuple("jQuery", "JavaScript"),
                        tuple("NodeJS", "JavaScript"), tuple("Hibernate", "Java"));
    }

    @Test
    public void testCount() throws Exception {
        Long result = frameworkGridDataDAO.count(new GridViewInputSpec(), false);
        assertThat(result).isEqualTo(6L);
    }

    @Test
    public void testCountWithFilter() throws Exception {
        Long result = frameworkGridDataDAO.count(new GridViewInputSpec(), true);
        assertThat(result).isEqualTo(6L);
    }
}