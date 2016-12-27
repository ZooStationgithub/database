package nl.zoostation.database.dao.impl;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.grid.IdNameGridRow;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author valentinnastasi
 */
@DataSet(locations = "/datasets/existing_pl.xml", qualifiedTableNames = true, tearDownOperation = DBOperation.DELETE)
public class SimpleGridDataDAOTest extends BaseDAOTest {

    @Autowired
    private SimpleGridDataDAO<ProgrammingLanguage, IdNameGridRow> programmingLanguageGridDataDAO;


    @Test
    public void testGetGridData() throws Exception {


    }
}