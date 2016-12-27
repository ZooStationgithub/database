package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.form.ProfileSearchFormObject;
import nl.zoostation.database.model.form.ProfileSearchFormWrapper;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.service.IProfileSearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author valentinnastasi
 */
public class ProfileSearchService extends SimpleGridDataService<ProfileGridRow> implements IProfileSearchService {

    private final IGenericEntityDAO<Country, Long> countryDAO;
    private final IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;
    private final IGenericEntityDAO<CompanyType, Long> companyTypeDAO;
    private final IGenericEntityDAO<ContractType, Long> contractTypeDAO;
    private final IGenericEntityDAO<Framework, Long> frameworkDAO;
    private final IGenericEntityDAO<RankType, Long> rankTypeDAO;
    private final IGenericEntityDAO<RoleType, Long> roleTypeDAO;

    public ProfileSearchService(
            IGridDataDAO<ProfileGridRow> profileGridDataDAO,
            IGenericEntityDAO<Country, Long> countryDAO,
            IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO,
            IGenericEntityDAO<CompanyType, Long> companyTypeDAO,
            IGenericEntityDAO<ContractType, Long> contractTypeDAO,
            IGenericEntityDAO<Framework, Long> frameworkDAO,
            IGenericEntityDAO<RankType, Long> rankTypeDAO,
            IGenericEntityDAO<RoleType, Long> roleTypeDAO) {

        super(profileGridDataDAO);
        this.countryDAO = countryDAO;
        this.programmingLanguageDAO = programmingLanguageDAO;
        this.companyTypeDAO = companyTypeDAO;
        this.contractTypeDAO = contractTypeDAO;
        this.frameworkDAO = frameworkDAO;
        this.rankTypeDAO = rankTypeDAO;
        this.roleTypeDAO = roleTypeDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileSearchFormWrapper prepareForm() {
        logger.debug("Preparing profile search form");
        ProfileSearchFormWrapper formWrapper = new ProfileSearchFormWrapper();
        formWrapper.setForm(new ProfileSearchFormObject());
        formWrapper.setRoleTypes(roleTypeDAO.findAll());
        formWrapper.setRankTypes(rankTypeDAO.findAll());
        formWrapper.setProgrammingLanguages(programmingLanguageDAO.findAll());
        formWrapper.setFrameworks(frameworkDAO.findAll());
        formWrapper.setCompanyTypes(companyTypeDAO.findAll());
        formWrapper.setContractTypes(contractTypeDAO.findAll());
        formWrapper.setCountries(countryDAO.findAll());
        return formWrapper;
    }

}
