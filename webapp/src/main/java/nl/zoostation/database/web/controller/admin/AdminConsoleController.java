package nl.zoostation.database.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("Duplicates")
@Controller
public class AdminConsoleController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String openAdminConsolePage() {
        return "/admin/adminConsole";
    }

}
