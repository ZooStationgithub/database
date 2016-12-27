package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.FrameworkFormObject;
import nl.zoostation.database.model.form.FrameworkFormWrapper;
import nl.zoostation.database.model.grid.FrameworkGridRow;
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
@RequestMapping("/admin/data/framework")
public class FrameworkDataController extends AbstractAdminTabController<FrameworkGridRow, FrameworkFormObject, FrameworkFormWrapper> {

    private static final Logger logger = LogManager.getLogger(FrameworkDataController.class);

    @Autowired
    public FrameworkDataController(
            IGridDataService<FrameworkGridRow> frameworkGridDataService,
            IFormService<?, Long, FrameworkFormObject, FrameworkFormWrapper> frameworkFormService,
            MessageSource messageSource) {
        super(frameworkGridDataService, frameworkFormService, messageSource);
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        logger.trace("Now handling request '/admin/data/framework/tab GET'");
        return super.openTab();
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<FrameworkGridRow> getGridData(DataTablesRequest dataTablesRequest) {
        logger.trace("Now handling request '/admin/data/framework/grid GET' with dataTablesRequest {}", dataTablesRequest);
        return super.getGridData(dataTablesRequest);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            @RequestParam("id") Optional<Long> id,
            Model model) {
        logger.trace("Now handling request '/admin/data/framework/form  GET' with ID {}", id);
        return super.openForm(id, model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("framework") @Valid FrameworkFormObject form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("Now handling request '/admin/data/framework POST' with form object {}", form);
        return super.save(form, bindingResult, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        logger.trace("Now handling request '/admin/data/framework/{id} DELETE' with ID {}", id);
        return super.delete(id);
    }

    @Override
    protected String getTabViewName() {
        return "/admin/framework/frameworkTab";
    }

    @Override
    protected String getFormViewName() {
        return "/admin/framework/frameworkForm";
    }

    @Override
    protected String getFormModelName() {
        return "framework";
    }

    @Override
    protected void populateModel(Model model, FrameworkFormWrapper formWrapper) {
        model.addAttribute("programmingLanguages", formWrapper.getProgrammingLanguages());
    }

}
