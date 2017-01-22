package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.exception.DatabaseException;
import nl.zoostation.database.model.domain.ContractType;
import nl.zoostation.database.model.form.IdNameFormObject;
import nl.zoostation.database.model.form.SimpleFormWrapper;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IFormService;
import nl.zoostation.database.service.IGridDataService;
import nl.zoostation.database.web.controller.ContextAwareBaseControllerTest;
import org.junit.After;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valentinnastasi
 */
public class ContractTypeDataControllerTest extends ContextAwareBaseControllerTest {

    private static final long ID = 1L;
    private static final String NAME = "Freelance";

    @Autowired
    private IGridDataService<IdNameGridRow> contractTypeGridDataService;

    @Autowired
    private IFormService<ContractType, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> contractTypeFormService;

    @After
    public void tearDown() throws Exception {
        Mockito.reset(contractTypeGridDataService, contractTypeFormService);
    }

    @Test
    public void testOpenTab() throws Exception {
        getMockMvc().perform(get("/admin/data/contract/tab"))
                .andExpect(view().name("/admin/contract/contractTab"));
    }

    @Test
    public void testGetGridData() throws Exception {
        IdNameGridRow row = new IdNameGridRow(ID, NAME);
        GridViewOutputSpec<IdNameGridRow> gridViewOutputSpec = new GridViewOutputSpec<>(1L, 1L, Collections.singletonList(row));

        ArgumentCaptor<GridViewInputSpec> argumentCaptor = ArgumentCaptor.forClass(GridViewInputSpec.class);
        when(contractTypeGridDataService.getGridData(argumentCaptor.capture())).thenReturn(gridViewOutputSpec);

        getMockMvc().perform(
                get("/admin/data/contract/grid")
                        .param("draw", "2")
                        .param("columns[0][data]", "id")
                        .param("columns[0][name]", "")
                        .param("columns[0][searchable]", "true")
                        .param("columns[0][orderable]", "true")
                        .param("columns[0][search][value]", "")
                        .param("columns[0][search][regex]", "false")
                        .param("columns[1][data]", "name")
                        .param("columns[1][name]", "")
                        .param("columns[1][searchable]", "true")
                        .param("columns[1][orderable]", "true")
                        .param("columns[1][search][value]", "")
                        .param("columns[1][search][regex]", "false")
                        .param("order[0][column]", "0")
                        .param("order[0][dir]", "asc")
                        .param("start", "0")
                        .param("length", "10")
                        .param("search[value]", "aqwe")
                        .param("search[regex]", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.draw", is(2)))
                .andExpect(jsonPath("$.recordsTotal", is(1)))
                .andExpect(jsonPath("$.recordsFiltered", is(1)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is((int) ID)))
                .andExpect(jsonPath("$.data[0].name", is(NAME)))
                .andExpect(jsonPath("$.error", is((Object) null)));

        GridViewInputSpec gridViewInputSpec = argumentCaptor.getValue();
        assertThat(gridViewInputSpec).isNotNull();
        assertThat(gridViewInputSpec.getGlobalFilter()).isEqualTo("aqwe");
    }

    @Test
    public void testOpenForm() throws Exception {
        IdNameFormObject formObject = new IdNameFormObject();
        SimpleFormWrapper<IdNameFormObject> formWrapper = new SimpleFormWrapper<>(formObject);

        when(contractTypeFormService.prepareForm(Optional.of(ID))).thenReturn(formWrapper);

        getMockMvc().perform(get("/admin/data/contract/form").param("id", String.valueOf(ID)))
                .andExpect(view().name("/admin/contract/contractForm"))
                .andExpect(model().attribute("contractType", is(formObject)));
    }

    @Test
    public void testSaveValidationError() throws Exception {
        getMockMvc().perform(
                post("/admin/data/contract")
                        .param("id", String.valueOf(ID))
                        .param("name", ""))
                .andExpect(view().name("/admin/contract/contractForm"))
                .andExpect(model().attributeExists("contractType"))
                .andExpect(model().attributeHasFieldErrors("contractType", "name"));
    }

    @Test
    public void testSaveSuccess() throws Exception {
        getMockMvc().perform(
                post("/admin/data/contract")
                        .param("id", String.valueOf(ID))
                        .param("name", NAME))
                .andExpect(status().isCreated())
                .andExpect(content().string(is("")));

        ArgumentCaptor<IdNameFormObject> argumentCaptor = ArgumentCaptor.forClass(IdNameFormObject.class);
        verify(contractTypeFormService).save(argumentCaptor.capture());

        IdNameFormObject formObject = argumentCaptor.getValue();
        assertThat(formObject).isNotNull();
        assertThat(formObject.getId()).isEqualTo(ID);
        assertThat(formObject.getName()).isEqualTo(NAME);
    }

    @Test
    public void testDeleteObjectInUse() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(contractTypeFormService).delete(ID);

        getMockMvc().perform(delete("/admin/data/contract/" + ID))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.httpCode", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .andExpect(jsonPath("$.exceptionClass", is(DatabaseException.class.getName())))
                .andExpect(jsonPath("$.message", is(not(empty()))));
    }

    @Test
    public void testDelete() throws Exception {
        getMockMvc().perform(delete("/admin/data/contract/" + ID))
                .andExpect(status().isNoContent());

        verify(contractTypeFormService).delete(ID);
    }
}