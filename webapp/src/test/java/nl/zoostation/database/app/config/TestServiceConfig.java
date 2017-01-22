package nl.zoostation.database.app.config;

import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.form.*;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.service.*;
import nl.zoostation.database.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("unchecked")
@Configuration
public class TestServiceConfig {

    @Bean(name = "accountFormService")
    public IAccountFormService createAccountFormService() {
        return mock(AccountFormService.class);
    }

    @Bean(name = "companyTypeFormService")
    public IFormService<CompanyType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createCompanyTypeFormService() {
        return mock(SimpleFormService.class);
    }

    @Bean(name = "contractTypeFormService")
    public IFormService<ContractType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createContractTypeFormService() {
        return mock(SimpleFormService.class);
    }

    @Bean(name = "frameworkFormService")
    public IFormService<Framework, Long, FrameworkFormObject, FrameworkFormWrapper> createFrameworkFormService() {
        return mock(FrameworkFormService.class);
    }

    @Bean(name = "profileFormService")
    public IFormService<Profile, Long, ProfileFormObject, ProfileFormWrapper> createProfileFormService() {
        return mock(ProfileFormService.class);
    }

    @Bean(name = "programmingLanguageFormService")
    public IFormService<ProgrammingLanguage, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createProgrammingLanguageFormService() {
        return mock(SimpleFormService.class);
    }

    @Bean(name = "rankTypeFormService")
    public IFormService<RankType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createRankTypeFormService() {
        return mock(SimpleFormService.class);
    }

    @Bean(name = "roleTypeFormService")
    public IFormService<RoleType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createRoleTypeFormService() {
        return mock(SimpleFormService.class);
    }

    @Bean(name = "accountGridDataService")
    public IGridDataService<AccountGridRow> createAccountGridDataService() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "frameworkGridDataService")
    public IGridDataService<FrameworkGridRow> createFrameworkGridDataService() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "profileGridDataService")
    public IGridDataService<AccountGridRow> createAccountGridDataDAO() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "companyTypeGridDataService")
    public IGridDataService<IdNameGridRow> createCompanyTypeGridDataService() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "roleTypeGridDataService")
    public IGridDataService<IdNameGridRow> createRoleTypeGridDataService() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "contractTypeGridDataService")
    public IGridDataService<IdNameGridRow> createContractTypeGridDataService() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "rankTypeGridDataService")
    public IGridDataService<IdNameGridRow> createRankTypeGridDataService() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "programmingLanguageGridDataService")
    public IGridDataService<IdNameGridRow> createProgrammingLanguageTypeGridDataService() {
        return mock(SimpleGridDataService.class);
    }

    @Bean(name = "accountManagementService")
    public IAccountManagementService createAccountActivationService() {
        return mock(AccountManagementService.class);
    }

    @Bean(name = "profileSearchService")
    public IProfileSearchService createProfileSearchService() {
        return mock(ProfileSearchService.class);
    }

    @Bean(name = "profileDetailsService")
    public IProfileDetailsService createProfileDetailsService() {
        return mock(ProfileDetailsService.class);
    }

}
