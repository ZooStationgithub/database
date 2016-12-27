package nl.zoostation.database.web.controller.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.zoostation.database.model.form.ProfileFormObject;
import nl.zoostation.database.model.form.ProfileFormWrapper;
import nl.zoostation.database.service.IFormService;
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

    private final IFormService<?, Long, ProfileFormObject, ProfileFormWrapper> profileFormService;

    @Autowired
    public ProfileManagementController(
            IFormService<?, Long, ProfileFormObject, ProfileFormWrapper> profileFormService) {
        this.profileFormService = profileFormService;
    }

    @RequestMapping(value = "/developer/edit", method = RequestMethod.GET)
    public String openEditPage(@RequestParam("u") Optional<Long> id, Model model) throws JsonProcessingException {
        logger.trace("Now handling request '/developer/edit GET' with ID {}", id);
        ProfileFormWrapper formWrapper = profileFormService.prepareForm(id);
        model.addAttribute("profile", formWrapper.getForm());
        model.addAttribute("languages", formWrapper.getProgrammingLanguages());
        model.addAttribute("frameworks", formWrapper.getFrameworks());
        model.addAttribute("companyTypes", formWrapper.getCompanyTypes());
        model.addAttribute("contractTypes", formWrapper.getContractTypes());
        model.addAttribute("rankTypes", formWrapper.getRankTypes());
        model.addAttribute("roleTypes", formWrapper.getRoleTypes());
        model.addAttribute("countries", formWrapper.getCountries());

        return "/developerForm";
    }

    @RequestMapping(value = "/developer/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ProfileFormObject profileForm) {
        logger.trace("Now handling request '/developer/edit POST' with form object {}", profileForm);
        profileFormService.save(profileForm);
    }

    @RequestMapping(value = "/developer", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam("u") Long id) {
        logger.trace("Now handling request '/developer DELETE' with ID {}", id);
        profileFormService.delete(id);
    }

}
