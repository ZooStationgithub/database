package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.IdNameFormObject;
import nl.zoostation.database.model.form.SimpleFormWrapper;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.service.IFormService;
import nl.zoostation.database.service.IGridDataService;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
@Controller
@RequestMapping("/admin/data/company")
public class CompanyTypeDataController extends AbstractAdminTabController<IdNameGridRow, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> {

    private static final Logger logger = LogManager.getLogger(CompanyTypeDataController.class);

    @Autowired
    protected CompanyTypeDataController(
            IGridDataService<IdNameGridRow> companyTypeGridDataService,
            IFormService<?, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> companyTypeFormService,
            MessageSource messageSource) {
        super(companyTypeGridDataService, companyTypeFormService, messageSource);
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        logger.trace("Now handling request '/admin/data/company GET'");
        return super.openTab();
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<IdNameGridRow> getGridData(DataTablesRequest dataTablesRequest) {
        logger.trace("Now handling request '/admin/data/company/grid GET' with dataTablesRequest {}", dataTablesRequest);
        return super.getGridData(dataTablesRequest);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            @RequestParam("id") Optional<Long> id,
            Model model) {
        logger.trace("Now handling request '/admin/data/company/form GET' with ID {}", id);
        return super.openForm(id, model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("companyType") @Valid IdNameFormObject form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("Now handling request '/admin/data/company POST' with form object {}", form);
        return super.save(form, bindingResult, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        logger.trace("Now handling request '/admin/data/company/{id} DELETE' with ID {}", id);
        return super.delete(id);
    }

    @Override
    protected String getTabViewName() {
        return "/admin/company/companyTab";
    }

    @Override
    protected String getFormViewName() {
        return "/admin/company/companyForm";
    }

    @Override
    protected String getFormModelName() {
        return "companyType";
    }

    @Override
    protected void populateModel(Model model, SimpleFormWrapper<IdNameFormObject> formWrapper) {
    }


}
