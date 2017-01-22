package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.exception.DatabaseException;
import nl.zoostation.database.model.domain.Framework;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.form.FrameworkFormObject;
import nl.zoostation.database.model.form.FrameworkFormWrapper;
import nl.zoostation.database.model.grid.FrameworkGridRow;
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
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valentinnastasi
 */
public class FrameworkDataControllerTest extends ContextAwareBaseControllerTest {

    private static final long ID = 1L;
    private static final String NAME = "Spring";
    private static final long PL_ID = 2L;
    private static final String PL_NAME = "Java";

    @Autowired
    private IGridDataService<FrameworkGridRow> frameworkGridDataService;

    @Autowired
    private IFormService<Framework, Long, FrameworkFormObject, FrameworkFormWrapper> frameworkFormService;

    @After
    public void tearDown() throws Exception {
        Mockito.reset(frameworkGridDataService, frameworkFormService);
    }

    @Test
    public void testOpenTab() throws Exception {
        getMockMvc().perform(get("/admin/data/framework/tab"))
                .andExpect(view().name("/admin/framework/frameworkTab"));
    }

    @Test
    public void testGetGridData() throws Exception {
        FrameworkGridRow row = new FrameworkGridRow(ID, NAME, PL_NAME);
        GridViewOutputSpec<FrameworkGridRow> gridViewOutputSpec = new GridViewOutputSpec<>(1L, 1L, Collections.singletonList(row));

        ArgumentCaptor<GridViewInputSpec> argumentCaptor = ArgumentCaptor.forClass(GridViewInputSpec.class);
        when(frameworkGridDataService.getGridData(argumentCaptor.capture())).thenReturn(gridViewOutputSpec);

        getMockMvc().perform(
                get("/admin/data/framework/grid")
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
                        .param("columns[2][data]", "programmingLanguageName")
                        .param("columns[2][name]", "")
                        .param("columns[2][searchable]", "true")
                        .param("columns[2][orderable]", "true")
                        .param("columns[2][search][value]", "")
                        .param("columns[2][search][regex]", "false")
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
                .andExpect(jsonPath("$.data[0].programmingLanguageName", is(PL_NAME)))
                .andExpect(jsonPath("$.error", is((Object) null)));

        GridViewInputSpec gridViewInputSpec = argumentCaptor.getValue();
        assertThat(gridViewInputSpec).isNotNull();
        assertThat(gridViewInputSpec.getGlobalFilter()).isEqualTo("aqwe");
    }

    @Test
    public void testOpenForm() throws Exception {
        List<ProgrammingLanguage> programmingLanguages = Collections.singletonList(new ProgrammingLanguage(PL_ID, PL_NAME));
        FrameworkFormObject formObject = new FrameworkFormObject();
        FrameworkFormWrapper formWrapper = new FrameworkFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setProgrammingLanguages(programmingLanguages);

        when(frameworkFormService.prepareForm(Optional.of(ID))).thenReturn(formWrapper);

        getMockMvc().perform(get("/admin/data/framework/form").param("id", String.valueOf(ID)))
                .andExpect(view().name("/admin/framework/frameworkForm"))
                .andExpect(model().attribute("framework", is(formObject)))
                .andExpect(model().attribute("programmingLanguages", is(programmingLanguages)));
    }

    @Test
    public void testSaveValidationError() throws Exception {
        List<ProgrammingLanguage> programmingLanguages = Collections.singletonList(new ProgrammingLanguage(PL_ID, PL_NAME));
        FrameworkFormObject formObject = new FrameworkFormObject();
        FrameworkFormWrapper formWrapper = new FrameworkFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setProgrammingLanguages(programmingLanguages);

        when(frameworkFormService.prepareForm(Optional.empty())).thenReturn(formWrapper);

        getMockMvc().perform(
                post("/admin/data/framework")
                        .param("id", String.valueOf(ID))
                        .param("name", "")
                        .param("programmingLanguageId", ""))
                .andExpect(view().name("/admin/framework/frameworkForm"))
                .andExpect(model().attributeExists("framework"))
                .andExpect(model().attributeHasFieldErrors("framework", "name", "programmingLanguageId"));
    }

    @Test
    public void testSaveSuccess() throws Exception {
        getMockMvc().perform(
                post("/admin/data/framework")
                        .param("id", String.valueOf(ID))
                        .param("name", NAME)
                        .param("programmingLanguageId", String.valueOf(PL_ID)))
                .andExpect(status().isCreated())
                .andExpect(content().string(is("")));

        ArgumentCaptor<FrameworkFormObject> argumentCaptor = ArgumentCaptor.forClass(FrameworkFormObject.class);
        verify(frameworkFormService).save(argumentCaptor.capture());

        FrameworkFormObject formObject = argumentCaptor.getValue();
        assertThat(formObject).isNotNull();
        assertThat(formObject.getId()).isEqualTo(ID);
        assertThat(formObject.getName()).isEqualTo(NAME);
        assertThat(formObject.getProgrammingLanguageId()).isEqualTo(PL_ID);
    }

    @Test
    public void testDeleteObjectInUse() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(frameworkFormService).delete(ID);

        getMockMvc().perform(delete("/admin/data/framework/" + ID))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.httpCode", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .andExpect(jsonPath("$.exceptionClass", is(DatabaseException.class.getName())))
                .andExpect(jsonPath("$.message", is(not(empty()))));
    }

    @Test
    public void testDelete() throws Exception {
        getMockMvc().perform(delete("/admin/data/framework/" + ID))
                .andExpect(status().isNoContent());

        verify(frameworkFormService).delete(ID);
    }
}