package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.ICompanyTypeService;
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
@RequestMapping("/admin/data/company")
public class CompanyTypeDataController {

    private final ICompanyTypeService companyTypeService;

    @Autowired
    public CompanyTypeDataController(ICompanyTypeService companyTypeService) {
        this.companyTypeService = companyTypeService;
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        return "/admin/company/companyTab";
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<IdNameGridRow> getGridData(DataTablesRequest request) {
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec(request.getPageStart(), request.getPageLength(), request.getGlobalFilter(),
                request.getOrderColumn(), request.getOrderDirection(), request.getFilterableColumns());
        GridViewOutputSpec<IdNameGridRow> outputSpec = companyTypeService.getGridData(gridViewInputSpec);
        DataTablesResponse<IdNameGridRow> response = new DataTablesResponse<>(request.getDrawCounter(), outputSpec.getTotalRecords(),
                outputSpec.getFilteredRecords(), outputSpec.getRecords());

        return response;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            @RequestParam("id") Optional<Long> id,
            Model model) {

        IdNameForm form = companyTypeService.prepareForm(id);
        model.addAttribute("companyType", form);

        return "/admin/company/companyForm";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("companyType") @Valid IdNameForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/admin/company/companyForm";
        }

        companyTypeService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        companyTypeService.delete(id);
    }
    
}
