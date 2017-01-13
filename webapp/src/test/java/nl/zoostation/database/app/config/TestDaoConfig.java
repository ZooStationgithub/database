package nl.zoostation.database.app.config;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.dao.impl.*;
import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.view.ProfileView;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.mock;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("unchecked")
@Configuration
public class TestDaoConfig {

    @Bean(name = "accountDAO")
    public IAccountDAO createAccountDAO() {
        return mock(AccountDAO.class);
    }

    @Bean(name = "accountGroupDAO")
    public IGenericEntityDAO<AccountGroup, Long> createAccountGroupDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "companyTypeDAO")
    public IGenericEntityDAO<CompanyType, Long> createCompanyTypeDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "contractTypeDAO")
    public IGenericEntityDAO<ContractType, Long> createContractTypeDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "countryDAO")
    public IGenericEntityDAO<Country, Long> createCountryDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "frameworkDAO")
    public IGenericEntityDAO<Framework, Long> createFrameworkDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "profileDAO")
    public IGenericEntityDAO<Profile, Long> createProfileDAO() {
        return mock(ProfileDAO.class);
    }

    @Bean(name = "programmingLanguageDAO")
    public IGenericEntityDAO<ProgrammingLanguage, Long> createProgrammingLanguageDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "rankTypeDAO")
    public IGenericEntityDAO<RankType, Long> createRankTypeDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "roleTypeDAO")
    public IGenericEntityDAO<RoleType, Long> createRoleTypeDAO() {
        return mock(SimpleGenericEntityDAO.class);
    }

    @Bean(name = "profileDetailsViewDAO")
    public IGenericReadOnlyEntityDAO<ProfileView, Long> createProfileDetailsViewDAO() {
        return mock(ProfileDetailsViewDAO.class);
    }

    @Bean(name = "accountGridDataDAO")
    public IGridDataDAO<AccountGridRow> createAccountGridDataDAO() {
        return mock(AccountGridDataDAO.class);
    }

    @Bean(name = "companyTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createCompanyTypeGridDataDAO() {
        return mock(NamedEntityGridDataDAO.class);
    }

    @Bean(name = "contractTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createContractTypeGridDataDAO() {
        return mock(NamedEntityGridDataDAO.class);
    }

    @Bean(name = "frameworkGridDataDAO")
    public IGridDataDAO<FrameworkGridRow> createFrameworkGridDataDAO() {
        return mock(FrameworkGridDataDAO.class);
    }

    @Bean(name = "profileGridDataDAO")
    public IGridDataDAO<ProfileGridRow> createProfileGridDataDAO() {
        return mock(ProfileGridDataDAO.class);
    }

    @Bean(name = "programmingLanguageGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createProgrammingLanguageGridDataDAO() {
        return mock(NamedEntityGridDataDAO.class);
    }

    @Bean(name = "rankTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createRankTypeGridDataDAO() {
        return mock(NamedEntityGridDataDAO.class);
    }

    @Bean(name = "roleTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createRoleTypeGridDataDAO() {
        return mock(NamedEntityGridDataDAO.class);
    }

}
