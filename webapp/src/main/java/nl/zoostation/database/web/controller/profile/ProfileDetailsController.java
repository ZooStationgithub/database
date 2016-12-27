package nl.zoostation.database.web.controller.profile;

import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.IProfileDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author valentinnastasi
 */
@Controller
public class ProfileDetailsController {

    private static final Logger logger = LogManager.getLogger(ProfileDetailsController.class);

    private final IProfileDetailsService profileDetailsService;

    public ProfileDetailsController(IProfileDetailsService profileDetailsService) {
        this.profileDetailsService = profileDetailsService;
    }

    @RequestMapping("/developer")
    public String openDetailsPage(@RequestParam("u") Long id, Model model) {
        logger.trace("Now handling request '/developer GET' with ID {}", id);
        ProfileView profileView = profileDetailsService.getDetails(id);
        model.addAttribute("profile", profileView);
        return "/developerDetails";
    }

    @RequestMapping("/developer/info")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void requestMoreInfo(@RequestParam("u") Long id) {
        logger.trace("Now handling request '/developer/info GET' for ID {}", id);
        profileDetailsService.requestMoreInfo(id);
    }

}
