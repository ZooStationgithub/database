package nl.zoostation.database.web.controller;

import nl.zoostation.database.model.form.AccountForm;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.GridViewInputSpec;
import nl.zoostation.database.model.grid.GridViewOutputSpec;
import nl.zoostation.database.service.IAccountService;
import nl.zoostation.database.service.IGridDataService;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
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
    private final IGridDataService<AccountGridRow> accountGridDataService;

    @Autowired
    public AccountManagementController(
            IAccountService accountService,
            IGridDataService<AccountGridRow> accountGridDataService) {

        this.accountService = accountService;
        this.accountGridDataService = accountGridDataService;
    }

    @RequestMapping(value = "/account/list", method = RequestMethod.GET)
    public String openAccountListPage() {
        return "/accountList";
    }

    @RequestMapping(value = "/account/grid", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResponse<AccountGridRow> getGridData(DataTablesRequest request) {
        logger.debug("Now processing request '/account/grid GET' with request {}", request);
        GridViewInputSpec inputSpec = new GridViewInputSpec(request.getPageStart(), request.getPageLength(), request.getGlobalFilter(),
                request.getOrderColumn(), request.getOrderDirection(), request.getFilterableColumns());
        GridViewOutputSpec<AccountGridRow> outputSpec = accountGridDataService.getGridData(inputSpec);
        DataTablesResponse<AccountGridRow> response = new DataTablesResponse<>(request.getDrawCounter(), outputSpec.getTotalRecords(),
                outputSpec.getFilteredRecords(), outputSpec.getRecords());

        return response;
    }

    @RequestMapping(value = "/account/form", method = RequestMethod.GET)
    public String openCreateAccountPage(Model model) {
        logger.debug("Now Processing request: '/account/form GET'");
        model.addAttribute("account", new AccountForm());
        model.addAttribute("groups", accountService.findGroups());
        return "/accountForm";
    }

    @RequestMapping(value = "/account/form", method = RequestMethod.POST)
    public String createAccount(@ModelAttribute("account") @Valid AccountForm account, BindingResult bindingResult, Model model) {
        logger.debug("Processing request '/account/form POST' for form object {}", account);
        if (!StringUtils.equals(account.getPassword(), account.getConfirmPassword())) {
            bindingResult.addError(new FieldError("account", "confirmPassword", account.getConfirmPassword(), false, new String[] { "NoMatch.account.confirmPassword" }, null, ""));
        }
        if (bindingResult.hasErrors()) {
            logger.debug("Form validation failed");
            model.addAttribute("groups", accountService.findGroups());
            return "/accountForm";
        }
        logger.debug("Form validation succeeded");
        accountService.create(account);
        return "redirect:/account/list";
    }

    @RequestMapping(value = "/account/form/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAccount(@PathVariable Long id) {
        logger.debug("Processing request '/account/form DELETE' with id {}", id);
        accountService.delete(id);
    }

    @RequestMapping(value = "/account/activate", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateAccount(@RequestParam("u") Long id) {
        logger.debug("Processing request '/account/activate GET' for account id {}", id);
        accountService.activate(id);
    }

    @RequestMapping(value = "/account/activate/resend/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resendActivationLink(@PathVariable Long id) {
        logger.debug("Processing request '/account/activate/resend POST' for account id {}", id);
        accountService.resendActivation(id);
    }

}
