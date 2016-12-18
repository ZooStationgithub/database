package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.AccountFormObject;
import nl.zoostation.database.model.form.AccountFormWrapper;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.service.IAccountFormService;
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
import java.util.Optional;

/**
 * @author valentinnastasi
 */
@SuppressWarnings({"Duplicates", "UnnecessaryLocalVariable"})
@Controller
@RequestMapping("/admin/account")
public class AccountController extends AbstractAdminTabController<AccountGridRow, AccountFormObject, AccountFormWrapper> {

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    @Autowired
    public AccountController(
            IGridDataService<AccountGridRow> accountGridDataService,
            IAccountFormService accountFormService) {
        super(accountGridDataService, accountFormService);
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        return super.openTab();
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResponse<AccountGridRow> getGridData(DataTablesRequest dataTablesRequest) {
        logger.debug("Now processing request '/account/grid GET' with dataTablesRequest {}", dataTablesRequest);
        return super.getGridData(dataTablesRequest);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(Model model) {
        logger.debug("Now Processing request: '/admin/account/form GET'");
        return super.openForm(Optional.empty(), model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("account") @Valid AccountFormObject account,
            BindingResult bindingResult,
            Model model) {

        logger.debug("Processing request '/admin/account POST' for form object {}", account);

        if (!StringUtils.equals(account.getPassword(), account.getConfirmPassword())) {
            bindingResult.addError(new FieldError("account", "confirmPassword", account.getConfirmPassword(), false, new String[]{"NoMatch.account.confirmPassword"}, null, ""));
        }

        return super.save(account, bindingResult, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        logger.debug("Now processing request");
        super.delete(id);
    }

    @Override
    protected String getTabViewName() {
        return "/admin/account/accountTab";
    }

    @Override
    protected String getFormViewName() {
        return "/admin/account/accountForm";
    }

    @Override
    protected String getFormModelName() {
        return "account";
    }

    @Override
    protected void populateModel(Model model, AccountFormWrapper formWrapper) {
        model.addAttribute("groups", formWrapper.getAccountGroups());
    }

}
