package nl.zoostation.database.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author valentinnastasi
 */
@Controller
public class AuthorizationController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String openLoginPage() {
        return "/login";
    }

}
