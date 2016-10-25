package nl.zoostation.database.web.controller;

import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.IProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;

/**
 * @author valentinnastasi
 */
@Controller
public class ProfileDetailsController {

    private static final Logger logger = LogManager.getLogger(ProfileDetailsController.class);

    private final IProfileService profileService;

    @Autowired
    public ProfileDetailsController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping("/developer")
    public String openDetailsPage(@RequestParam("u") Long id, Model model) {
        logger.debug("Now handling request '/developer GET' with id '{}'", id);
        ProfileView profileView = profileService.findById(id);
        model.addAttribute("profile", profileView);
        return "/developerDetails";
    }

    @RequestMapping("/developer/info")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void requestMoreInfo(@RequestParam("u") Long id) {
        logger.debug("Now handling request '/developer/info GET' for id {}", id);
        profileService.requestMoreInfo(id);
    }

}
