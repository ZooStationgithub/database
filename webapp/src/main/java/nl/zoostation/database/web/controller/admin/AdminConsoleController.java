package nl.zoostation.database.web.controller.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("Duplicates")
@Controller
public class AdminConsoleController {

    private static final Logger logger = LogManager.getLogger(AdminConsoleController.class);

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String openAdminConsolePage() {
        logger.trace("Now handling request '/admin GET'");
        return "/admin/adminConsole";
    }

}
