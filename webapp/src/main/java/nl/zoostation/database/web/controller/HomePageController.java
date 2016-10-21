package nl.zoostation.database.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Controller for home page.</p>
 *
 * @author valentinnastasi
 */
@Controller
public class HomePageController {

    private static final Logger logger = LogManager.getLogger(HomePageController.class);

    @RequestMapping({"/", "/home", "/index"})
    public String openHomePage() {
        logger.debug("Opening home page");
        return "/index";
    }

}
