package nl.zoostation.database.dao.impl;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import com.excilys.ebi.spring.dbunit.test.ExpectedDataSet;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.domain.Profile;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author val
 */
@Ignore("Some problem with DBUnit")
@DataSet(locations = {"/datasets/existing_role_types.xml", "/datasets/existing_rank_types.xml", "/datasets/existing_contract_types.xml",
        "/datasets/existing_company_types.xml", "/datasets/existing_pl.xml", "/datasets/existing_frameworks.xml", "/datasets/existing_countries.xml"},
        tearDownOperation = DBOperation.DELETE)
public class ProfileDAOTest extends BaseDAOTest {

    @Autowired
    private ProfileDAO profileDAO;

    @DataSet(locations = {"/datasets/existing_profiles.xml", "/datasets/existing_known_frameworks.xml", "/datasets/existing_preferred_company_types.xml",
            "/datasets/existing_preferred_countries.xml", "/datasets/existing_profile_ranks.xml", "/datasets/existing_profile_roles.xml"},
            tearDownOperation = DBOperation.DELETE)
    @ExpectedDataSet(locations = {"/datasets/remaining_profiles.xml", "/datasets/remaining_known_frameworks.xml", "/datasets/remaining_preferred_company_types.xml",
            "/datasets/remaining_preferred_countries.xml", "/datasets/remaining_profile_ranks.xml", "/datasets/existing_profile_roles.xml"})
    @Test
    public void testDelete() throws Exception {
        Profile profile = profileDAO.findOne(1002L).orElseThrow(NullPointerException::new);
        profileDAO.delete(profile);
        profileDAO.flush();
    }
}