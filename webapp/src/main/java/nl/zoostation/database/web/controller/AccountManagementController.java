package nl.zoostation.database.web.controller;

import nl.zoostation.database.model.form.AccountForm;
import nl.zoostation.database.service.IAccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author valentinnastasi
 */
@Controller
public class AccountManagementController {

    private static final Logger logger = LogManager.getLogger(AccountManagementController.class);

    private final IAccountService accountService;

    @Autowired
    public AccountManagementController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String openCreateAccountPage(Model model) {
        logger.debug("Now Processing request: '/account GET'");
        model.addAttribute("account", new AccountForm());
        model.addAttribute("groups", accountService.findGroups());
        return "/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute("account") @Valid AccountForm account, BindingResult bindingResult, Model model) {
        logger.debug("Processing request '/account POST' for form object {}", account);
        if (!StringUtils.equals(account.getPassword(), account.getConfirmPassword())) {
            bindingResult.addError(new FieldError("account", "confirmPassword", account.getConfirmPassword(), false, new String[] { "NoMatch.account.confirmPassword" }, null, ""));
        }
        if (bindingResult.hasErrors()) {
            logger.debug("Form validation failed");
            model.addAttribute("groups", accountService.findGroups());
            return "/account";
        }
        logger.debug("Form validation succeeded");
        accountService.create(account);
        return "redirect:/index";
    }

    @RequestMapping(value = "/account/activate", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateAccount(@RequestParam("u") Long id) {
        logger.debug("Processing request '/account/activate GET' for account id {}", id);
        accountService.activate(id);
    }

    @RequestMapping(value = "/account/activate/resend", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resendActivationLink(@RequestParam("u") Long id) {
        logger.debug("Processing request '/account/activate/resend POST' for account id {}", id);
        accountService.resendActivation(id);
    }

}
