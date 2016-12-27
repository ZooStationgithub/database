package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.exception.DatabaseException;
import nl.zoostation.database.model.form.IFormObject;
import nl.zoostation.database.model.form.IFormWrapper;
import nl.zoostation.database.model.grid.IGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.model.json.JsonErrorResponse;
import nl.zoostation.database.service.IFormService;
import nl.zoostation.database.service.IGridDataService;
import nl.zoostation.database.web.datatables.DataTablesRequest;
import nl.zoostation.database.web.datatables.DataTablesResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public abstract class AbstractAdminTabController<G extends IGridRow<Long>, F extends IFormObject<Long>, W extends IFormWrapper<F>> {

    private final IGridDataService<G> gridDataService;
    private final IFormService<?, Long, F, W> formService;
    private final MessageSource messageSource;

    protected AbstractAdminTabController(
            IGridDataService<G> gridDataService,
            IFormService<?, Long, F, W> formService,
            MessageSource messageSource) {

        this.gridDataService = gridDataService;
        this.formService = formService;
        this.messageSource = messageSource;
    }

    public String openTab() {
        return getTabViewName();
    }

    public DataTablesResponse<G> getGridData(DataTablesRequest request) {
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec(request.getPageStart(), request.getPageLength(), request.getGlobalFilter(),
                request.getOrderColumn(), request.getOrderDirection(), request.getFilterableColumns());
        GridViewOutputSpec<G> outputSpec = gridDataService.getGridData(gridViewInputSpec);
        DataTablesResponse<G> response = new DataTablesResponse<>(request.getDrawCounter(), outputSpec.getTotalRecords(),
                outputSpec.getFilteredRecords(), outputSpec.getRecords());

        return response;
    }

    public String openForm(Optional<Long> id, Model model) {
        W formWrapper = formService.prepareForm(id);
        model.addAttribute(getFormModelName(), formWrapper.getForm());
        populateModel(model, formWrapper);
        return getFormViewName();
    }

    public Object save(F form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            W formWrapper = formService.prepareForm(Optional.empty());
            populateModel(model, formWrapper);
            return getFormViewName();
        }

        formService.save(form);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    public ResponseEntity delete(Long id) {
        try {
            formService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            JsonErrorResponse jsonErrorResponse = new JsonErrorResponse();
            jsonErrorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            jsonErrorResponse.setExceptionClass(DatabaseException.class.getName());
            jsonErrorResponse.setMessage(messageSource.getMessage("form.common.delete.inUse", null, LocaleContextHolder.getLocale()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonErrorResponse);
        }
    }

    protected abstract String getTabViewName();

    protected abstract String getFormViewName();

    protected abstract String getFormModelName();

    protected abstract void populateModel(Model model, W formWrapper);

    protected IGridDataService<G> getGridDataService() {
        return gridDataService;
    }

    protected IFormService<?, Long, F, W> getFormService() {
        return formService;
    }

}
