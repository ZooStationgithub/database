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
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author valentinnastasi
 */
@Configuration
public class DaoConfig {

    @Bean(name = "accountDAO")
    public IAccountDAO createAccountDAO(SessionFactory sessionFactory) {
        return new AccountDAO(sessionFactory);
    }

    @Bean(name = "accountGroupDAO")
    public IGenericEntityDAO<AccountGroup, Long> createAccountGroupDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, AccountGroup.class, Long.class);
    }

    @Bean(name = "companyTypeDAO")
    public IGenericEntityDAO<CompanyType, Long> createCompanyTypeDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, CompanyType.class, Long.class);
    }

    @Bean(name = "contractTypeDAO")
    public IGenericEntityDAO<ContractType, Long> createContractTypeDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, ContractType.class, Long.class);
    }

    @Bean(name = "countryDAO")
    public IGenericEntityDAO<Country, Long> createCountryDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, Country.class, Long.class);
    }

    @Bean(name = "frameworkDAO")
    public IGenericEntityDAO<Framework, Long> createFrameworkDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, Framework.class, Long.class);
    }

    @Bean(name = "profileDAO")
    public IGenericEntityDAO<Profile, Long> createProfileDAO(SessionFactory sessionFactory) {
        return new ProfileDAO(sessionFactory);
    }

    @Bean(name = "programmingLanguageDAO")
    public IGenericEntityDAO<ProgrammingLanguage, Long> createProgrammingLanguageDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, ProgrammingLanguage.class, Long.class);
    }

    @Bean(name = "rankTypeDAO")
    public IGenericEntityDAO<RankType, Long> createRankTypeDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, RankType.class, Long.class);
    }

    @Bean(name = "roleTypeDAO")
    public IGenericEntityDAO<RoleType, Long> createRoleTypeDAO(SessionFactory sessionFactory) {
        return new SimpleGenericEntityDAO<>(sessionFactory, RoleType.class, Long.class);
    }

    @Bean(name = "profileDetailsViewDAO")
    public IGenericReadOnlyEntityDAO<ProfileView, Long> createProfileDetailsViewDAO(SessionFactory sessionFactory) {
        return new ProfileDetailsViewDAO(sessionFactory);
    }

    @Bean(name = "accountGridDataDAO")
    public IGridDataDAO<AccountGridRow> createAccountGridDataDAO(SessionFactory sessionFactory) {
        return new AccountGridDataDAO(sessionFactory);
    }

    @Bean(name = "companyTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createCompanyTypeGridDataDAO(SessionFactory sessionFactory) {
        return new NamedEntityGridDataDAO<>(sessionFactory, CompanyType.class);
    }

    @Bean(name = "contractTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createContractTypeGridDataDAO(SessionFactory sessionFactory) {
        return new NamedEntityGridDataDAO<>(sessionFactory, ContractType.class);
    }

    @Bean(name = "frameworkGridDataDAO")
    public IGridDataDAO<FrameworkGridRow> createFrameworkGridDataDAO(SessionFactory sessionFactory) {
        return new FrameworkGridDataDAO(sessionFactory);
    }

    @Bean(name = "profileGridDataDAO")
    public IGridDataDAO<ProfileGridRow> createProfileGridDataDAO(SessionFactory sessionFactory) {
        return new ProfileGridDataDAO(sessionFactory);
    }

    @Bean(name = "programmingLanguageGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createProgrammingLanguageGridDataDAO(SessionFactory sessionFactory) {
        return new NamedEntityGridDataDAO<>(sessionFactory, ProgrammingLanguage.class);
    }

    @Bean(name = "rankTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createRankTypeGridDataDAO(SessionFactory sessionFactory) {
        return new NamedEntityGridDataDAO<>(sessionFactory, RankType.class);
    }

    @Bean(name = "roleTypeGridDataDAO")
    public IGridDataDAO<IdNameGridRow> createRoleTypeGridDataDAO(SessionFactory sessionFactory) {
        return new NamedEntityGridDataDAO<>(sessionFactory, RoleType.class);
    }

}
