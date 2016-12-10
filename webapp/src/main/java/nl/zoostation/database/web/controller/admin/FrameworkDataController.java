package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.FrameworkForm;
import nl.zoostation.database.model.form.FrameworkFormContainer;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IFrameworkService;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class FrameworkDataController {

    private final IFrameworkService frameworkService;

    @Autowired
    public FrameworkDataController(IFrameworkService frameworkService) {
        this.frameworkService = frameworkService;
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        return "/admin/framework/frameworkTab";
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<FrameworkGridRow> getGridData(DataTablesRequest request) {
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec(request.getPageStart(), request.getPageLength(), request.getGlobalFilter(),
                request.getOrderColumn(), request.getOrderDirection(), request.getFilterableColumns());
        GridViewOutputSpec<FrameworkGridRow> outputSpec = frameworkService.getGridData(gridViewInputSpec);
        DataTablesResponse<FrameworkGridRow> response = new DataTablesResponse<>(request.getDrawCounter(), outputSpec.getTotalRecords(),
                outputSpec.getFilteredRecords(), outputSpec.getRecords());

        return response;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            @RequestParam("id") Optional<Long> id,
            Model model) {

        FrameworkFormContainer formContainer = frameworkService.prepareForm(id);
        model.addAttribute("framework", formContainer.getFrameworkForm());
        model.addAttribute("programmingLanguages", formContainer.getProgrammingLanguages());

        return "/admin/framework/frameworkForm";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("framework") @Valid FrameworkForm form,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("programmingLanguages", frameworkService.prepareForm(Optional.empty()).getProgrammingLanguages());
            return "/admin/framework/frameworkForm";
        }

        frameworkService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        frameworkService.delete(id);
    }

}
