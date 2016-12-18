package nl.zoostation.database.web.controller;

import nl.zoostation.database.service.IAccountManagementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author valentinnastasi
 */
@Controller
public class AccountActivationController {

    private static final Logger logger = LogManager.getLogger(AccountActivationController.class);

    private final IAccountManagementService accountActivationService;

    @Autowired
    public AccountActivationController(IAccountManagementService accountActivationService) {
        this.accountActivationService = accountActivationService;
    }

    @RequestMapping(value = "/account/activate", method = RequestMethod.GET)
    public String activateAccount(@RequestParam("u") Long id) {
        logger.debug("Processing request '/account/activate GET' for account id {}", id);
        accountActivationService.activate(id);
        return "/accountActivated";
    }

    @RequestMapping(value = "/admin/account/activate/resend/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resendActivationLink(@PathVariable("id") Long id) {
        logger.debug("Processing request '/account/activate/resend POST' for account id {}", id);
        accountActivationService.resendActivation(id);
    }

}
