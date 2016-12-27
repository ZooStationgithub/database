package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.domain.ContractType;
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
@RequestMapping("/admin/data/contract")
public class ContractTypeDataController extends AbstractAdminTabController<IdNameGridRow, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> {

    private static final Logger logger = LogManager.getLogger(ContractTypeDataController.class);

    @Autowired
    public ContractTypeDataController(
            IGridDataService<IdNameGridRow> contractTypeGridDataService,
            IFormService<?, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> contractTypeFormService,
            MessageSource messageSource) {
        super(contractTypeGridDataService, contractTypeFormService, messageSource);
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        logger.trace("Now handling request '/admin/data/contract/tab GET'");
        return super.openTab();
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<IdNameGridRow> getGridData(DataTablesRequest dataTablesRequest) {
        logger.trace("Now handling request '/admin/data/contract/grid GET' with dataTablesRequest {}", dataTablesRequest);
        return super.getGridData(dataTablesRequest);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            @RequestParam("id") Optional<Long> id,
            Model model) {
        logger.trace("Now handling request '/admin/data/contract/form GET' with ID {}", id);
        return super.openForm(id, model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("contractType") @Valid IdNameFormObject form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("Now handling request '/admin/data/contract POST' with form object {}", form);
        return super.save(form, bindingResult, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        logger.trace("Now handling request '/admin/data/contract/{id} DELETE' with ID {}", id);
        return super.delete(id);
    }

    @Override
    protected String getTabViewName() {
        return "/admin/contract/contractTab";
    }

    @Override
    protected String getFormViewName() {
        return "/admin/contract/contractForm";
    }

    @Override
    protected String getFormModelName() {
        return "contractType";
    }

    @Override
    protected void populateModel(Model model, SimpleFormWrapper<IdNameFormObject> formWrapper) {
    }

}
