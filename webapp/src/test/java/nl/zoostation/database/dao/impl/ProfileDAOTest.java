package nl.zoostation.database.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.domain.Profile;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author val
 */
@DatabaseSetup({"/datasets/common/role_types.xml", "/datasets/common/rank_types.xml", "/datasets/common/company_types.xml",
        "/datasets/common/contract_types.xml", "/datasets/common/countries.xml", "/datasets/common/programming_languages.xml", "/datasets/common/frameworks.xml"})
public class ProfileDAOTest extends BaseDAOTest {

    @Autowired
    private ProfileDAO profileDAO;

    @Test
    @DatabaseSetup("/datasets/common/profiles.xml")
    @ExpectedDatabase(value = "/datasets/ProfileDAOTest/delete_expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void testDelete() throws Exception {
        Profile profile = profileDAO.findOne(1002L).orElseThrow(NullPointerException::new);
        profileDAO.delete(profile);
        profileDAO.flush();
    }
}