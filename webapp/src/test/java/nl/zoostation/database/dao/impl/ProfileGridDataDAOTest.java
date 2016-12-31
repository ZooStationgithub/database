package nl.zoostation.database.dao.impl;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.form.ProfileSearchFormObject;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static nl.zoostation.database.app.Constants.Parameters.SEARCH_FILTER;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author val
 */
@DataSet(locations = {"/datasets/existing_role_types.xml", "/datasets/existing_rank_types.xml", "/datasets/existing_contract_types.xml",
        "/datasets/existing_company_types.xml", "/datasets/existing_pl.xml", "/datasets/existing_frameworks.xml", "/datasets/existing_countries.xml",
        "/datasets/existing_profiles.xml", "/datasets/existing_known_frameworks.xml", "/datasets/existing_preferred_company_types.xml",
        "/datasets/existing_preferred_countries.xml", "/datasets/existing_profile_roles.xml", "/datasets/existing_profile_ranks.xml"},
        tearDownOperation = DBOperation.DELETE)
public class ProfileGridDataDAOTest extends BaseDAOTest {

    @Autowired
    private ProfileGridDataDAO profileGridDataDAO;

    @Test
    public void testGetRowsNoFilter() throws Exception {
        ProfileSearchFormObject searchFormObject = new ProfileSearchFormObject();
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, searchFormObject);
        List<ProfileGridRow> rows = profileGridDataDAO.getRows(gridViewInputSpec);
        assertThat(rows).isNotNull().hasSize(3).usingFieldByFieldElementComparator()
                .contains(new ProfileGridRow(1000L, "Junior", "Java", "Python", 89, "Belgium"),
                        new ProfileGridRow(1001L, null, "JavaScript", null, null, "USA"),
                        new ProfileGridRow(1002L, "Junior", null, null, 99, "Ireland"));
    }

    @Test
    public void testCountNoFilter() throws Exception {
        ProfileSearchFormObject searchFormObject = new ProfileSearchFormObject();
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, searchFormObject);
        Long result = profileGridDataDAO.count(gridViewInputSpec, true);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void testGetRowsWithFilterPreferredCity() throws Exception {
        ProfileSearchFormObject searchFormObject = new ProfileSearchFormObject();
        searchFormObject.setPreferredCity("Lei");
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, searchFormObject);
        List<ProfileGridRow> rows = profileGridDataDAO.getRows(gridViewInputSpec);
        assertThat(rows).isNotNull().hasSize(1).usingFieldByFieldElementComparator()
                .contains(new ProfileGridRow(1000L, "Junior", "Java", "Python", 89, "Belgium"));
    }

    @Test
    public void testGetRowsWithFilterMainProgrammingLanguage() throws Exception {
        ProfileSearchFormObject searchFormObject = new ProfileSearchFormObject();
        searchFormObject.setMainProgrammingLanguageId(1003L);
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, searchFormObject);
        List<ProfileGridRow> rows = profileGridDataDAO.getRows(gridViewInputSpec);
        assertThat(rows).isNotNull().hasSize(1).usingFieldByFieldElementComparator()
                .contains(new ProfileGridRow(1001L, null, "JavaScript", null, null, "USA"));
    }
}