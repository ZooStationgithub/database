package nl.zoostation.database.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
@Controller
public class AuthorizationController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String openLoginPage() {
        return "/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String performLogout(HttpServletRequest request, HttpServletResponse response) {
        Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).ifPresent(
                a -> new SecurityContextLogoutHandler().logout(request, response, a));
        return "redirect:/home";
    }

}
