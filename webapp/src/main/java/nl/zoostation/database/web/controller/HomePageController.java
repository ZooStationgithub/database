package nl.zoostation.database.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.SearchFilter;
import nl.zoostation.database.model.grid.SearchQueryContainer;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.model.input.SearchToken;
import nl.zoostation.database.service.IProfileSearchService;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static nl.zoostation.database.app.Constants.Parameters.SEARCH_FILTER;

/**
 * <p>Controller for home page.</p>
 *
 * @author valentinnastasi
 */
@Controller
public class HomePageController {

    private static final Logger logger = LogManager.getLogger(HomePageController.class);

    private final ObjectMapper objectMapper;
    private final IProfileSearchService profileSearchService;

    @Autowired
    public HomePageController(ObjectMapper objectMapper, IProfileSearchService profileSearchService) {
        this.objectMapper = objectMapper;
        this.profileSearchService = profileSearchService;
    }

    @RequestMapping({"/", "/home", "/index"})
    public String openHomePage(HttpSession httpSession, Model model) throws JsonProcessingException {
        logger.debug("Opening home page");

        if (httpSession.getAttribute(SEARCH_FILTER) == null) {
            httpSession.setAttribute(SEARCH_FILTER, new SearchFilter());
        }

        SearchQueryContainer searchQueryContainer = profileSearchService.prepareForm((SearchFilter) httpSession.getAttribute(SEARCH_FILTER));
        //model.addAttribute("countries", searchQueryContainer.getCountries());
        model.addAttribute("languages", searchQueryContainer.getProgrammingLanguages());
        model.addAttribute("frameworks", searchQueryContainer.getFrameworks());
        model.addAttribute("companyTypes", searchQueryContainer.getCompanyTypes());
        model.addAttribute("contractTypes", searchQueryContainer.getContractTypes());
        model.addAttribute("rankTypes", searchQueryContainer.getRankTypes());
        model.addAttribute("roleTypes", searchQueryContainer.getRoleTypes());
        model.addAttribute("selectedOriginCountry", serialize(searchQueryContainer.getSelectedOriginCountry()));
        model.addAttribute("selectedPreferredCountries", serialize(searchQueryContainer.getSelectedPreferredCountries()));

        return "/index";
    }

    @RequestMapping(value = "/profile/grid", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResponse getGridData(HttpSession httpSession, DataTablesRequest request) throws IOException {
        logger.debug("Handling request '/profile/grid GET'");

        SearchFilter searchFilter = deserializeSearchFilter(request.getExtras().get(SEARCH_FILTER));
        httpSession.setAttribute(SEARCH_FILTER, searchFilter);

        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, searchFilter);

        GridViewOutputSpec<ProfileGridRow> gridViewOutputSpec = profileSearchService.getGridData(gridViewInputSpec);

        DataTablesResponse<ProfileGridRow> response = new DataTablesResponse<>(request.getDrawCounter(), gridViewOutputSpec.getTotalRecords(),
                gridViewOutputSpec.getFilteredRecords(), gridViewOutputSpec.getRecords());

        return response;
    }

    @RequestMapping(value = "/profile/country/tokens", method = RequestMethod.GET)
    @ResponseBody
    public List<? extends SearchToken> getCountryTokens(@RequestParam("q") String searchTerm) {
        return profileSearchService.findTokens(searchTerm, Collections.emptyMap());
    }

    private SearchFilter deserializeSearchFilter(String json) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return new SearchFilter();
        }
        return Optional.ofNullable(objectMapper.readValue(json, SearchFilter.class)).orElse(new SearchFilter());
    }

    private String serialize(Object o) throws JsonProcessingException {
        if (o == null) {
            return "";
        }

        return objectMapper.writeValueAsString(o);
    }

}
