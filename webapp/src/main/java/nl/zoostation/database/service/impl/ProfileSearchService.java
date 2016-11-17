package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.*;
import nl.zoostation.database.model.grid.SearchQuery;
import nl.zoostation.database.model.grid.SearchQueryContainer;
import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.IProfileSearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author valentinnastasi
 */
@Service
public class ProfileSearchService implements IProfileSearchService {

    private final ICountryDAO countryDAO;
    private final IProgrammingLanguageDAO programmingLanguageDAO;
    private final ICompanyTypeDAO companyTypeDAO;
    private final IContractTypeDAO contractTypeDAO;
    private final IFrameworkDAO frameworkDAO;
    private final IRankTypeDAO rankTypeDAO;
    private final IRoleTypeDAO roleTypeDAO;

    public ProfileSearchService(
            ICountryDAO countryDAO,
            IProgrammingLanguageDAO programmingLanguageDAO,
            ICompanyTypeDAO companyTypeDAO,
            IContractTypeDAO contractTypeDAO,
            IFrameworkDAO frameworkDAO,
            IRankTypeDAO rankTypeDAO,
            IRoleTypeDAO roleTypeDAO) {

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
    public SearchQueryContainer prepareForm() {
        SearchQueryContainer searchQueryContainer = new SearchQueryContainer();
        searchQueryContainer.setSearchQuery(new SearchQuery());
        searchQueryContainer.setRoleTypes(roleTypeDAO.findAll());
        searchQueryContainer.setRankTypes(rankTypeDAO.findAll());
        searchQueryContainer.setProgrammingLanguages(programmingLanguageDAO.findAll());
        searchQueryContainer.setFrameworks(frameworkDAO.findAll());
        searchQueryContainer.setCompanyTypes(companyTypeDAO.findAll());
        searchQueryContainer.setContractTypes(contractTypeDAO.findAll());
        searchQueryContainer.setCountries(countryDAO.findAll());
        return searchQueryContainer;
    }

    @Override
    public List<ProfileView> getListItems(SearchQuery searchQuery) {
        return null;
    }
}
