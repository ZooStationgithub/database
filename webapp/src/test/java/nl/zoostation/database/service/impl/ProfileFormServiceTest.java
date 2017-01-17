package nl.zoostation.database.service.impl;

import nl.zoostation.database.app.tools.MapBuilder;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.form.ProfileFormObject;
import nl.zoostation.database.model.form.ProfileFormWrapper;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("unchecked")
public class ProfileFormServiceTest extends BaseServiceTest {

    private static final long ID = 1L;
    private static final String ZS_NUMBER = "ertyvubin";
    private static final int ENGLISH_LEVEL = 1;
    private static final long MPL_ID = 1;
    private static final Long[] COUNTRY_IDS = {1L, 2L};

    @Autowired
    private IGenericEntityDAO<Country, Long> countryDAO;

    @Autowired
    private IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;

    @Autowired
    private IGenericEntityDAO<CompanyType, Long> companyTypeDAO;

    @Autowired
    private IGenericEntityDAO<ContractType, Long> contractTypeDAO;

    @Autowired
    private IGenericEntityDAO<Framework, Long> frameworkDAO;

    @Autowired
    private IGenericEntityDAO<RankType, Long> rankTypeDAO;

    @Autowired
    private IGenericEntityDAO<RoleType, Long> roleTypeDAO;

    @Autowired
    private IGenericEntityDAO<Profile, Long> profileDAO;

    @Autowired
    private ProfileFormService profileFormService;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        initFormLists();
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(countryDAO, programmingLanguageDAO, companyTypeDAO, contractTypeDAO, frameworkDAO, rankTypeDAO, roleTypeDAO, profileDAO);
    }

    @Test
    public void testPrepareFormNew() throws Exception {
        when(profileDAO.create()).thenReturn(new Profile());

        ProfileFormWrapper formWrapper = profileFormService.prepareForm(Optional.empty());
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull().isEqualToComparingFieldByField(new ProfileFormObject());
        assertThat(formWrapper.getCountries()).isNotNull().hasSize(2);
        assertThat(formWrapper.getProgrammingLanguages()).isNotNull().hasSize(1);
        assertThat(formWrapper.getCompanyTypes()).isNotNull().hasSize(1);
        assertThat(formWrapper.getContractTypes()).isNotNull().hasSize(1);
        assertThat(formWrapper.getFrameworks()).isNotNull().hasSize(1);
        assertThat(formWrapper.getRankTypes()).isNotNull().hasSize(1);
        assertThat(formWrapper.getRoleTypes()).isNotNull().hasSize(1);

        verify(profileDAO, times(0)).findOne(any(Long.class));
    }

    @Test
    public void testPrepareFormExisting() throws Exception {
        Profile profile = createProfile();
        when(profileDAO.findOne(ID)).thenReturn(Optional.of(profile));

        ProfileFormWrapper formWrapper = profileFormService.prepareForm(Optional.of(ID));
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull();
        assertThat(formWrapper.getForm().getId()).isEqualTo(ID);
        assertThat(formWrapper.getForm().getZsNumber()).isEqualTo(ZS_NUMBER);
        assertThat(formWrapper.getForm().getEnglishLevel()).isEqualTo(ENGLISH_LEVEL);
        assertThat(formWrapper.getForm().getMainProgrammingLanguageId()).isEqualTo(MPL_ID);
        assertThat(formWrapper.getForm().getPreferredCountryIds()).hasSize(2).containsExactly(COUNTRY_IDS);
        assertThat(formWrapper.getForm().getCustomFields()).hasSize(1).contains(entry("name", "value"));
    }

    @Test
    public void testSave() throws Exception {
        ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(MPL_ID, "1");
        Country country1 = new Country(COUNTRY_IDS[0], "1", "1");
        Country country2 = new Country(COUNTRY_IDS[1], "2", "2");

        when(profileDAO.create()).thenReturn(new Profile());
        when(profileDAO.save(any(Profile.class))).then(returnsFirstArg());
        when(programmingLanguageDAO.findOne(MPL_ID)).thenReturn(Optional.of(programmingLanguage));
        when(countryDAO.findMany(Arrays.stream(COUNTRY_IDS).collect(toList()))).thenReturn(Arrays.asList(country1, country2));

        ProfileFormObject formObject = createProfileFormObject();

        profileFormService.save(formObject);

        ArgumentCaptor<Profile> argumentCaptor = ArgumentCaptor.forClass(Profile.class);
        verify(profileDAO).save(argumentCaptor.capture());

        Profile profile = argumentCaptor.getValue();
        assertThat(profile).isNotNull();
        assertThat(profile.getZoostationNumber()).isNotEmpty();
        assertThat(profile.getEnglishLevel()).isEqualTo(ENGLISH_LEVEL);
        assertThat(profile.getMainProgrammingLanguage()).containsSame(programmingLanguage);
        assertThat(profile.getPreferredCountries()).hasSize(2).contains(country1, country2);
        assertThat(profile.getCustomFields()).hasSize(1).extracting("fieldName", "fieldValue", "profile")
                .contains(tuple("name", "value", profile));
    }

    private void initFormLists() {
        when(countryDAO.findAll()).thenReturn(Arrays.asList(new Country(COUNTRY_IDS[0], "1", "1"), new Country(COUNTRY_IDS[1], "2", "2")));
        when(programmingLanguageDAO.findAll()).thenReturn(Collections.singletonList(new ProgrammingLanguage(MPL_ID, "1")));
        when(companyTypeDAO.findAll()).thenReturn(Collections.singletonList(new CompanyType(1L, "c")));
        when(contractTypeDAO.findAll()).thenReturn(Collections.singletonList(new ContractType(1L, "c")));
        when(frameworkDAO.findAll()).thenReturn(Collections.singletonList(new Framework(1L, "f")));
        when(rankTypeDAO.findAll()).thenReturn(Collections.singletonList(new RankType(1L, "r")));
        when(roleTypeDAO.findAll()).thenReturn(Collections.singletonList(new RoleType(1L, "r")));
    }

    private Profile createProfile() {
        Profile profile = new Profile();
        profile.setId(ID);
        profile.setZoostationNumber(ZS_NUMBER);
        profile.setEnglishLevel(ENGLISH_LEVEL);
        profile.setMainProgrammingLanguage(new ProgrammingLanguage(MPL_ID, "1"));
        profile.setPreferredCountries(Arrays.stream(COUNTRY_IDS).map(i -> new Country(i, String.valueOf(i), String.valueOf(i))).collect(Collectors.toSet()));
        profile.getCustomFields().add(new CustomProfileField("name", "value"));
        return profile;
    }

    private ProfileFormObject createProfileFormObject() {
        ProfileFormObject formObject = new ProfileFormObject();
        formObject.setEnglishLevel(ENGLISH_LEVEL);
        formObject.setMainProgrammingLanguageId(MPL_ID);
        formObject.setPreferredCountryIds(Arrays.asList(COUNTRY_IDS));
        formObject.setCustomFields(MapBuilder.<String, String>newHashMap().put("name", "value").build());
        return formObject;
    }

}