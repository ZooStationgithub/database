package nl.zoostation.database.app.config;

import nl.zoostation.database.dao.IAccountDAO;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.mail.impl.MailService;
import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.form.*;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.*;
import nl.zoostation.database.service.impl.*;
import nl.zoostation.database.service.listeners.TransactionEventListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author valentinnastasi
 */
@Configuration
@Import({DaoConfig.class, MailConfig.class, AopConfig.class})
public class ServiceConfig {

    @Bean
    public TransactionEventListeners transactionEventListeners() {
        return new TransactionEventListeners();
    }

    @Bean(name = "mailService")
    public IMailService createMailService(
            JavaMailSender mailSender,
            freemarker.template.Configuration freeMarkerConfiguration) {

        return new MailService(mailSender, freeMarkerConfiguration);
    }

    @Bean(name = "accountFormService")
    public IAccountFormService createAccountFormService(
            IGenericEntityDAO<Account, Long> accountDAO,
            IGenericEntityDAO<AccountGroup, Long> accountGroupDAO,
            IMailService mailService) {

        return new AccountFormService(accountDAO, accountGroupDAO, mailService);
    }

    @Bean(name = "companyTypeFormService")
    public IFormService<CompanyType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createCompanyTypeFormService(
            IGenericEntityDAO<CompanyType, Long> companyTypeDAO) {
        return new SimpleFormService<>(companyTypeDAO);
    }

    @Bean(name = "contractTypeFormService")
    public IFormService<ContractType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createContractTypeFormService(
            IGenericEntityDAO<ContractType, Long> contractTypeDAO) {
        return new SimpleFormService<>(contractTypeDAO);
    }

    @Bean(name = "frameworkFormService")
    public IFormService<Framework, Long, FrameworkFormObject, FrameworkFormWrapper> createFrameworkFormService(
            IGenericEntityDAO<Framework, Long> frameworkDAO,
            IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO) {

        return new FrameworkFormService(frameworkDAO, programmingLanguageDAO);
    }

    @Bean(name = "profileFormService")
    public IFormService<Profile, Long, ProfileFormObject, ProfileFormWrapper> createProfileFormService(
            IGenericEntityDAO<Profile, Long> profileDAO,
            IGenericEntityDAO<Country, Long> countryDAO,
            IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO,
            IGenericEntityDAO<CompanyType, Long> companyTypeDAO,
            IGenericEntityDAO<ContractType, Long> contractTypeDAO,
            IGenericEntityDAO<Framework, Long> frameworkDAO,
            IGenericEntityDAO<RankType, Long> rankTypeDAO,
            IGenericEntityDAO<RoleType, Long> roleTypeDAO) {
        return new ProfileFormService(profileDAO, countryDAO, programmingLanguageDAO, companyTypeDAO, contractTypeDAO,
                frameworkDAO, rankTypeDAO, roleTypeDAO);
    }

    @Bean(name = "programmingLanguageFormService")
    public IFormService<ProgrammingLanguage, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createProgrammingLanguageFormService(
            IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO) {
        return new SimpleFormService<>(programmingLanguageDAO);
    }

    @Bean(name = "rankTypeFormService")
    public IFormService<RankType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createRankTypeFormService(
            IGenericEntityDAO<RankType, Long> rankTypeDAO) {
        return new SimpleFormService<>(rankTypeDAO);
    }

    @Bean(name = "roleTypeFormService")
    public IFormService<RoleType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> createRoleTypeFormService(
            IGenericEntityDAO<RoleType, Long> roleTypeDAO) {
        return new SimpleFormService<>(roleTypeDAO);
    }

    @Bean(name = "accountGridDataService")
    public IGridDataService<AccountGridRow> createAccountGridDataService(
            IGridDataDAO<AccountGridRow> accountGridDataDAO) {
        return new SimpleGridDataService<>(accountGridDataDAO);
    }

    @Bean(name = "frameworkGridDataService")
    public IGridDataService<FrameworkGridRow> createFrameworkGridDataService(
            IGridDataDAO<FrameworkGridRow> frameworkGridDataDAO) {
        return new SimpleGridDataService<>(frameworkGridDataDAO);
    }

    @Bean(name = "profileGridDataService")
    public IGridDataService<AccountGridRow> createAccountGridDataDAO(
            IGridDataDAO<AccountGridRow> accountGridDataDAO) {
        return new SimpleGridDataService<>(accountGridDataDAO);
    }

    @Bean(name = "companyTypeGridDataService")
    public IGridDataService<IdNameGridRow> createCompanyTypeGridDataService(
            IGridDataDAO<IdNameGridRow> companyTypeGridDataDAO) {
        return new SimpleGridDataService<>(companyTypeGridDataDAO);
    }

    @Bean(name = "roleTypeGridDataService")
    public IGridDataService<IdNameGridRow> createRoleTypeGridDataService(
            IGridDataDAO<IdNameGridRow> roleTypeGridDataDAO) {
        return new SimpleGridDataService<>(roleTypeGridDataDAO);
    }

    @Bean(name = "contractTypeGridDataService")
    public IGridDataService<IdNameGridRow> createContractTypeGridDataService(
            IGridDataDAO<IdNameGridRow> contractTypeGridDataDAO) {
        return new SimpleGridDataService<>(contractTypeGridDataDAO);
    }

    @Bean(name = "rankTypeGridDataService")
    public IGridDataService<IdNameGridRow> createRankTypeGridDataService(
            IGridDataDAO<IdNameGridRow> rankTypeGridDataDAO) {
        return new SimpleGridDataService<>(rankTypeGridDataDAO);
    }

    @Bean(name = "programmingLanguageGridDataService")
    public IGridDataService<IdNameGridRow> createProgrammingLanguageTypeGridDataService(
            IGridDataDAO<IdNameGridRow> programmingLanguageGridDataDAO) {
        return new SimpleGridDataService<>(programmingLanguageGridDataDAO);
    }

    @Bean(name = "accountManagementService")
    public IAccountManagementService createAccountActivationService(
            IAccountDAO accountDAO,
            IMailService mailService,
            PasswordEncoder passwordEncoder) {
        return new AccountManagementService(accountDAO, mailService, passwordEncoder);
    }

    @Bean(name = "profileSearchService")
    public IProfileSearchService createProfileSearchService(
            IGridDataDAO<ProfileGridRow> profileGridDataDAO,
            IGenericEntityDAO<Country, Long> countryDAO,
            IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO,
            IGenericEntityDAO<CompanyType, Long> companyTypeDAO,
            IGenericEntityDAO<ContractType, Long> contractTypeDAO,
            IGenericEntityDAO<Framework, Long> frameworkDAO,
            IGenericEntityDAO<RankType, Long> rankTypeDAO,
            IGenericEntityDAO<RoleType, Long> roleTypeDAO) {

        return new ProfileSearchService(profileGridDataDAO, countryDAO, programmingLanguageDAO, companyTypeDAO,
                contractTypeDAO, frameworkDAO, rankTypeDAO, roleTypeDAO);
    }

    @Bean(name = "profileDetailsService")
    public IProfileDetailsService createProfileDetailsService(
            IGenericReadOnlyEntityDAO<ProfileView, Long> profileViewDAO,
            IGenericEntityDAO<Profile, Long> profileDAO,
            IMailService mailService) {
        return new ProfileDetailsService(profileViewDAO, profileDAO, mailService);
    }

}
