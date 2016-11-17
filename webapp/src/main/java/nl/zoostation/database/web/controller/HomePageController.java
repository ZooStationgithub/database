package nl.zoostation.database.web.controller;

import nl.zoostation.database.model.grid.SearchQuery;
import nl.zoostation.database.model.grid.SearchQueryContainer;
import nl.zoostation.database.service.IProfileSearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * <p>Controller for home page.</p>
 *
 * @author valentinnastasi
 */
@Controller
@SessionAttributes("searchQuery")
public class HomePageController {

    private static final Logger logger = LogManager.getLogger(HomePageController.class);

    private final IProfileSearchService profileSearchService;

    @Autowired
    public HomePageController(IProfileSearchService profileSearchService) {
        this.profileSearchService = profileSearchService;
    }

    @ModelAttribute("searchQuery")
    public SearchQuery createSearchQuery() {
        return new SearchQuery();
    }

    @RequestMapping({"/", "/home", "/index"})
    public String openHomePage(Model model) {
        logger.debug("Opening home page");
        SearchQueryContainer searchQueryContainer = profileSearchService.prepareForm();
        model.addAttribute("countries", searchQueryContainer.getCountries());
        model.addAttribute("languages", searchQueryContainer.getProgrammingLanguages());
        model.addAttribute("frameworks", searchQueryContainer.getFrameworks());
        model.addAttribute("companyTypes", searchQueryContainer.getCompanyTypes());
        model.addAttribute("contractTypes", searchQueryContainer.getContractTypes());
        model.addAttribute("rankTypes", searchQueryContainer.getRankTypes());
        model.addAttribute("roleTypes", searchQueryContainer.getRoleTypes());
        return "/index";
    }

    @RequestMapping("/index/refresh")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void refreshList(@ModelAttribute("searchQuery") SearchQuery searchQuery) {
        logger.debug("Handling request '/index/refresh GET'");
    }

}
