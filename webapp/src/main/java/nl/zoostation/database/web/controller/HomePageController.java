package nl.zoostation.database.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.zoostation.database.model.form.ProfileSearchFormObject;
import nl.zoostation.database.model.form.ProfileSearchFormWrapper;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
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
    public HomePageController(
            ObjectMapper objectMapper,
            IProfileSearchService profileSearchService) {
        this.objectMapper = objectMapper;
        this.profileSearchService = profileSearchService;
    }

    @RequestMapping({"/", "/home", "/index"})
    public String openHomePage(HttpSession httpSession, Model model) throws JsonProcessingException {
        logger.debug("Opening home page");

        ProfileSearchFormWrapper formWrapper = profileSearchService.prepareForm();
        if (Objects.isNull(httpSession.getAttribute(SEARCH_FILTER))) {
            httpSession.setAttribute(SEARCH_FILTER, formWrapper.getForm());
        }

        model.addAttribute("languages", formWrapper.getProgrammingLanguages());
        model.addAttribute("frameworks", formWrapper.getFrameworks());
        model.addAttribute("companyTypes", formWrapper.getCompanyTypes());
        model.addAttribute("contractTypes", formWrapper.getContractTypes());
        model.addAttribute("rankTypes", formWrapper.getRankTypes());
        model.addAttribute("roleTypes", formWrapper.getRoleTypes());
        model.addAttribute("countries", formWrapper.getCountries());

        return "/index";
    }

    @RequestMapping(value = "/profile/grid", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResponse getGridData(HttpSession httpSession, DataTablesRequest request) throws IOException {
        logger.debug("Handling request '/profile/grid GET'");

        ProfileSearchFormObject formObject = deserializeSearchFilter(request.getExtras().get(SEARCH_FILTER));
        httpSession.setAttribute(SEARCH_FILTER, formObject);

        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        gridViewInputSpec.getExtras().put(SEARCH_FILTER, formObject);

        GridViewOutputSpec<ProfileGridRow> gridViewOutputSpec = profileSearchService.getGridData(gridViewInputSpec);
        DataTablesResponse<ProfileGridRow> response = new DataTablesResponse<>();
        response.setDrawCounter(request.getDrawCounter());
        response.setRecords(gridViewOutputSpec.getRecords());
        response.setTotalRecords(gridViewOutputSpec.getTotalRecords());
        response.setFilteredRecords(gridViewOutputSpec.getFilteredRecords());

        return response;
    }

    private ProfileSearchFormObject deserializeSearchFilter(String json) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return new ProfileSearchFormObject();
        }
        return Optional.ofNullable(objectMapper.readValue(json, ProfileSearchFormObject.class))
                .orElse(new ProfileSearchFormObject());
    }

}
