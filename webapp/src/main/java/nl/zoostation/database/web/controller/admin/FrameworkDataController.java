package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.FrameworkFormObject;
import nl.zoostation.database.model.form.FrameworkFormWrapper;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import nl.zoostation.database.service.IFormService;
import nl.zoostation.database.service.IGridDataService;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    public FrameworkDataController(
            IGridDataService<FrameworkGridRow> frameworkGridDataService,
            IFormService<?, Long, FrameworkFormObject, FrameworkFormWrapper> frameworkFormService) {
        super(frameworkGridDataService, frameworkFormService);
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        return super.openTab();
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<FrameworkGridRow> getGridData(DataTablesRequest dataTablesRequest) {
        return super.getGridData(dataTablesRequest);
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            @RequestParam("id") Optional<Long> id,
            Model model) {
        return super.openForm(id, model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("framework") @Valid FrameworkFormObject form,
            BindingResult bindingResult,
            Model model) {

        return super.save(form, bindingResult, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        super.delete(id);
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
