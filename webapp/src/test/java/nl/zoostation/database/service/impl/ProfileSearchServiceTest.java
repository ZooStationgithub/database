package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.form.ProfileSearchFormWrapper;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("unchecked")
public class ProfileSearchServiceTest extends BaseServiceTest {

    @Autowired
    private  IGenericEntityDAO<Country, Long> countryDAO;

    @Autowired
    private  IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;

    @Autowired
    private  IGenericEntityDAO<CompanyType, Long> companyTypeDAO;

    @Autowired
    private  IGenericEntityDAO<ContractType, Long> contractTypeDAO;

    @Autowired
    private  IGenericEntityDAO<Framework, Long> frameworkDAO;

    @Autowired
    private  IGenericEntityDAO<RankType, Long> rankTypeDAO;

    @Autowired
    private  IGenericEntityDAO<RoleType, Long> roleTypeDAO;

    @Autowired
    private ProfileSearchService profileSearchService;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(countryDAO, programmingLanguageDAO, companyTypeDAO, contractTypeDAO, frameworkDAO, rankTypeDAO, roleTypeDAO);
        initFormLists();
    }

    @Test
    public void testPrepareForm() throws Exception {
        ProfileSearchFormWrapper formWrapper = profileSearchService.prepareForm();
        assertThat(formWrapper).isNotNull();
        assertThat(formWrapper.getForm()).isNotNull();
        assertThat(formWrapper.getProgrammingLanguages()).isNotNull().hasSize(1);
        assertThat(formWrapper.getCompanyTypes()).isNotNull().hasSize(1);
        assertThat(formWrapper.getContractTypes()).isNotNull().hasSize(1);
        assertThat(formWrapper.getRoleTypes()).isNotNull().hasSize(1);
        assertThat(formWrapper.getRankTypes()).isNotNull().hasSize(1);
        assertThat(formWrapper.getCountries()).isNotNull().hasSize(2);
    }

    private void initFormLists() {
        when(countryDAO.findAll()).thenReturn(Arrays.asList(new Country(1L, "1", "1"), new Country(2L, "2", "2")));
        when(programmingLanguageDAO.findAll()).thenReturn(Collections.singletonList(new ProgrammingLanguage(1L, "1")));
        when(companyTypeDAO.findAll()).thenReturn(Collections.singletonList(new CompanyType(1L, "c")));
        when(contractTypeDAO.findAll()).thenReturn(Collections.singletonList(new ContractType(1L, "c")));
        when(frameworkDAO.findAll()).thenReturn(Collections.singletonList(new Framework(1L, "f")));
        when(rankTypeDAO.findAll()).thenReturn(Collections.singletonList(new RankType(1L, "r")));
        when(roleTypeDAO.findAll()).thenReturn(Collections.singletonList(new RoleType(1L, "r")));
    }
}