package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.AccountForm;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IAccountService;
import nl.zoostation.database.service.IGridDataService;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("Duplicates")
@Controller
public class AccountController {

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    private final IAccountService accountService;
    private final IGridDataService<AccountGridRow> accountGridDataService;

    @Autowired
    public AccountController(
            IAccountService accountService,
            IGridDataService<AccountGridRow> accountGridDataService) {

        this.accountService = accountService;
        this.accountGridDataService = accountGridDataService;
    }


    @RequestMapping("/admin/account/tab")
    public String openAccountTab() {
        return "/admin/account/accountTab";
    }

    @RequestMapping(value = "/admin/account/grid", method = RequestMethod.GET)
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

    @RequestMapping(value = "/admin/account/form" ,method = RequestMethod.GET)
    public String openAccountForm(Model model) {
        logger.debug("Now Processing request: '/admin/account/form GET'");
        model.addAttribute("account", new AccountForm());
        model.addAttribute("groups", accountService.findGroups());
        return "/admin/account/accountForm";
    }

    @RequestMapping(value = "/admin/account", method = RequestMethod.POST)
    public Object save(@ModelAttribute("account") @Valid AccountForm account, BindingResult bindingResult, Model model) {
        logger.debug("Processing request '/admin/account POST' for form object {}", account);
        if (!StringUtils.equals(account.getPassword(), account.getConfirmPassword())) {
            bindingResult.addError(new FieldError("account", "confirmPassword", account.getConfirmPassword(), false, new String[] { "NoMatch.account.confirmPassword" }, null, ""));
        }
        if (bindingResult.hasErrors()) {
            logger.debug("Form validation failed");
            model.addAttribute("groups", accountService.findGroups());
            return "/admin/account/accountForm";
        }

        accountService.create(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/admin/account/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable("id") Long id) {

        logger.debug("Now processing request");
        accountService.delete(id);
    }

    @RequestMapping(value = "/admin/account/activate/resend/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void resendActivationLink(
            @PathVariable("id") Long id) {

        logger.debug("Processing request '/account/activate/resend POST' for account id {}", id);
        accountService.resendActivation(id);
    }


}
