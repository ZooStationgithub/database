package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IContractTypeService;
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
@RequestMapping("/admin/data/contract")
public class ContractTypeDataController {

    private final IContractTypeService contractTypeService;

    @Autowired
    public ContractTypeDataController(IContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @RequestMapping(value = "/tab", method = RequestMethod.GET)
    public String openTab() {
        return "/admin/contract/contractTab";
    }

    @RequestMapping(value = "/grid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public DataTablesResponse<IdNameGridRow> getGridData(DataTablesRequest request) {
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec(request.getPageStart(), request.getPageLength(), request.getGlobalFilter(),
                request.getOrderColumn(), request.getOrderDirection(), request.getFilterableColumns());
        GridViewOutputSpec<IdNameGridRow> outputSpec = contractTypeService.getGridData(gridViewInputSpec);
        DataTablesResponse<IdNameGridRow> response = new DataTablesResponse<>(request.getDrawCounter(), outputSpec.getTotalRecords(),
                outputSpec.getFilteredRecords(), outputSpec.getRecords());

        return response;
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String openForm(
            @RequestParam("id") Optional<Long> id,
            Model model) {

        IdNameForm form = contractTypeService.prepareForm(id);
        model.addAttribute("contractType", form);

        return "/admin/contract/contractForm";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(
            @ModelAttribute("contractType") @Valid IdNameForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/admin/contract/contractForm";
        }

        contractTypeService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        contractTypeService.delete(id);
    }

}
