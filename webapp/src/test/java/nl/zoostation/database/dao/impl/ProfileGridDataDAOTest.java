package nl.zoostation.database.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.form.ProfileSearchFormObject;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static nl.zoostation.database.app.Constants.Parameters.SEARCH_FILTER;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author val
 */
@DatabaseSetup({"/datasets/common/role_types.xml", "/datasets/common/rank_types.xml", "/datasets/common/company_types.xml",
        "/datasets/common/contract_types.xml", "/datasets/common/countries.xml", "/datasets/common/programming_languages.xml",
        "/datasets/common/frameworks.xml", "/datasets/common/profiles.xml"})
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

    @Test
    public void testGetRowsWithFilteredVisaNeeded() throws Exception {
        ProfileSearchFormObject searchFormObject = new ProfileSearchFormObject();
        searchFormObject.setVisaNeeded(true);
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, searchFormObject);
        List<ProfileGridRow> rows = profileGridDataDAO.getRows(gridViewInputSpec);
        assertThat(rows).isNotNull().hasSize(1).usingFieldByFieldElementComparator()
                .contains(new ProfileGridRow(1000L, "Junior", "Java", "Python", 89, "Belgium"));
    }

    @Test
    public void testGetRowsWithFilteredFrameworks() throws Exception {
        ProfileSearchFormObject searchFormObject = new ProfileSearchFormObject();
        searchFormObject.setKnownFrameworkIds(Arrays.asList(1000L, 1002L, 1003L));
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, searchFormObject);
        List<ProfileGridRow> rows = profileGridDataDAO.getRows(gridViewInputSpec);
        assertThat(rows).isNotNull().hasSize(2).usingFieldByFieldElementComparator()
                .contains(new ProfileGridRow(1000L, "Junior", "Java", "Python", 89, "Belgium"),
                        new ProfileGridRow(1002L, "Junior", null, null, 99, "Ireland"));
    }
}