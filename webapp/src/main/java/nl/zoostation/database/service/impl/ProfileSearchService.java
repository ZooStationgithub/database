package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.*;
import nl.zoostation.database.model.grid.*;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.model.input.SearchToken;
import nl.zoostation.database.service.IProfileSearchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private final IGridDataDAO<ProfileGridRow> profileGridDataDAO;

    public ProfileSearchService(
            ICountryDAO countryDAO,
            IProgrammingLanguageDAO programmingLanguageDAO,
            ICompanyTypeDAO companyTypeDAO,
            IContractTypeDAO contractTypeDAO,
            IFrameworkDAO frameworkDAO,
            IRankTypeDAO rankTypeDAO,
            IRoleTypeDAO roleTypeDAO,
            IGridDataDAO<ProfileGridRow> profileGridDataDAO) {

        this.countryDAO = countryDAO;
        this.programmingLanguageDAO = programmingLanguageDAO;
        this.companyTypeDAO = companyTypeDAO;
        this.contractTypeDAO = contractTypeDAO;
        this.frameworkDAO = frameworkDAO;
        this.rankTypeDAO = rankTypeDAO;
        this.roleTypeDAO = roleTypeDAO;
        this.profileGridDataDAO = profileGridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public SearchQueryContainer prepareForm() {
        SearchQueryContainer searchQueryContainer = new SearchQueryContainer();
        searchQueryContainer.setRoleTypes(roleTypeDAO.findAll());
        searchQueryContainer.setRankTypes(rankTypeDAO.findAll());
        searchQueryContainer.setProgrammingLanguages(programmingLanguageDAO.findAll());
        searchQueryContainer.setFrameworks(frameworkDAO.findAll());
        searchQueryContainer.setCompanyTypes(companyTypeDAO.findAll());
        searchQueryContainer.setContractTypes(contractTypeDAO.findAll());
        searchQueryContainer.setCountries(countryDAO.findAll());
        return searchQueryContainer;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<ProfileGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' is required");
        List<ProfileGridRow> rows = profileGridDataDAO.getRows(gridViewInputSpec);
        Long total = profileGridDataDAO.count(gridViewInputSpec, false);
        Long totalFiltered = profileGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<ProfileGridRow> gridViewOutputSpec = new GridViewOutputSpec<>();
        gridViewOutputSpec.setRecords(rows);
        gridViewOutputSpec.setTotalRecords(total);
        gridViewOutputSpec.setFilteredRecords(totalFiltered);
        return gridViewOutputSpec;
    }

    @Transactional(readOnly = true)
    @Override
    public List<? extends SearchToken> findTokens(String term, Map<String, Object> extras) {
        Objects.requireNonNull(term, () -> "Parameter 'term' must not be null");
        return countryDAO.findTokens(term, extras);
    }
}
