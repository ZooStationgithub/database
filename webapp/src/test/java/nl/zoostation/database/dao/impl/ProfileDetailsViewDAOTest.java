package nl.zoostation.database.dao.impl;

import com.excilys.ebi.spring.dbunit.config.DBOperation;
import com.excilys.ebi.spring.dbunit.test.DataSet;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.view.ProfileView;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author val
 */
@Ignore("Hibernate doesn't support creating views")
@DataSet(locations = {"/datasets/existing_role_types.xml", "/datasets/existing_rank_types.xml", "/datasets/existing_contract_types.xml",
        "/datasets/existing_company_types.xml", "/datasets/existing_pl.xml", "/datasets/existing_frameworks.xml", "/datasets/existing_countries.xml",
        "/datasets/existing_profiles.xml"}, tearDownOperation = DBOperation.DELETE)
public class ProfileDetailsViewDAOTest extends BaseDAOTest {

    @Autowired
    private ProfileDetailsViewDAO profileDetailsViewDAO;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void testFindOne() throws Exception {
        Optional<ProfileView> profileViewOptional = profileDetailsViewDAO.findOne(1000L);
        assertThat(profileViewOptional).isNotEmpty();
        ProfileView profileView = profileViewOptional.get();
        assertThat(profileView.getId()).isEqualTo(1000L);
        assertThat(profileView.getZoostationNumber()).isEqualTo("0001");
        assertThat(profileView.getMainProgrammingLanguage()).isEqualTo("Java");
        assertThat(profileView.getSecondProgrammingLanguage()).isEqualTo("Python");
        assertThat(profileView.getTestRating()).isEqualTo(89);
        assertThat(profileView.getOriginCountry()).isEqualTo("Belgium");
        assertThat(profileView.getVisaNeeded()).isTrue();
        assertThat(profileView.getExperience()).isEqualTo(5);
        assertThat(profileView.getWorkHistory()).isEqualTo("A sample text");
        assertThat(profileView.getEnglishLevel()).isEqualTo(5);
        assertThat(profileView.getTravelTime()).isEqualTo(10);
        assertThat(profileView.getPreferredCity()).isEqualTo("Leiden");
        assertThat(profileView.getAvailability()).isEqualTo(3);
        assertThat(profileView.getHoursPerWeek()).isEqualTo(40);
        assertThat(profileView.getRelocationReason()).isEqualTo("A sample text");
        assertThat(profileView.getContractType()).isEqualTo("Permanent");
        assertThat(profileView.getFrameworks()).isEqualTo("Qt; jQuery; Spring");
        assertThat(profileView.getPreferredCountries()).isEqualTo("Italy; Germany");
        assertThat(profileView.getPreferredCompanyTypes()).isEqualTo("Small");
        assertThat(profileView.getRoles()).isEqualTo("Developer");
        assertThat(profileView.getRank()).isEqualTo("Junior");
    }

}