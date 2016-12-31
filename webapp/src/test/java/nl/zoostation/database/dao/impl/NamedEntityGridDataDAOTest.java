package nl.zoostation.database.dao.impl;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author valentinnastasi
 */
@DataSet(locations = "/datasets/existing_pl.xml", tearDownOperation = DBOperation.DELETE)
public class NamedEntityGridDataDAOTest extends BaseDAOTest {

    @Autowired
    private NamedEntityGridDataDAO<ProgrammingLanguage> programmingLanguageGridDataDAO;

    @Test
    public void testGetRows() throws Exception {
        List<IdNameGridRow> rows = programmingLanguageGridDataDAO.getRows(new GridViewInputSpec());
        assertThat(rows).isNotNull().hasSize(5).extracting("name").contains("Java", "C++", "Python", "JavaScript", "Swift");
    }

    @Test
    public void testCountWithFilter() throws Exception {
        Long result = programmingLanguageGridDataDAO.count(new GridViewInputSpec(), true);
        assertThat(result).isNotNull().isEqualTo(5L);
    }

    @Test
    public void testCount() throws Exception {
        Long result = programmingLanguageGridDataDAO.count(new GridViewInputSpec(), false);
        assertThat(result).isNotNull().isEqualTo(5L);
    }
}