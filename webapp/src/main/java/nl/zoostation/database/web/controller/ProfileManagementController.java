package nl.zoostation.database.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.zoostation.database.model.form.ProfileForm;
import nl.zoostation.database.model.form.ProfileFormContainer;
import nl.zoostation.database.service.IProfileFormService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
@Controller
public class ProfileManagementController {

    private static final Logger logger = LogManager.getLogger(ProfileManagementController.class);

    private final IProfileFormService profileFormService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProfileManagementController(ObjectMapper objectMapper, IProfileFormService profileFormService) {
        this.profileFormService = profileFormService;
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/developer/edit", method = RequestMethod.GET)
    public String openEditPage(@RequestParam("u") Optional<Long> id, Model model) throws JsonProcessingException {
        logger.debug("Handling request '/developer/edit GET' with id {}", id);
        ProfileFormContainer profileFormContainer = profileFormService.prepareForm(id);
        model.addAttribute("profile", profileFormContainer.getProfileForm());
        model.addAttribute("languages", profileFormContainer.getProgrammingLanguages());
        model.addAttribute("frameworks", profileFormContainer.getFrameworks());
        model.addAttribute("companyTypes", profileFormContainer.getCompanyTypes());
        model.addAttribute("contractTypes", profileFormContainer.getContractTypes());
        model.addAttribute("rankTypes", profileFormContainer.getRankTypes());
        model.addAttribute("roleTypes", profileFormContainer.getRoleTypes());
        model.addAttribute("countries", profileFormContainer.getCountries());

        return "/developerForm";
    }

    @RequestMapping(value = "/developer/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ProfileForm profileForm) {
        logger.debug("Handling request '/developer/edit POST' with profile form {}", profileForm);
        profileFormService.saveProfile(profileForm);
    }

    @RequestMapping(value = "/developer/delete", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@RequestParam("u") Long id) {
        logger.debug("Handling request '/developer/delete DELETE' with id {}", id);
        profileFormService.delete(id);
    }

    private String serialize(Object o) throws JsonProcessingException {
        if (o == null) {
            return "";
        }

        return objectMapper.writeValueAsString(o);
    }

}
