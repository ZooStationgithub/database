package nl.zoostation.database.web.controller.profile;

import nl.zoostation.database.model.form.ProfileSearchFormObject;
import nl.zoostation.database.model.form.ProfileSearchFormWrapper;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IProfileSearchService;
import nl.zoostation.database.web.controller.ContextAwareBaseControllerTest;
import org.assertj.core.api.Condition;
import org.junit.After;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;

import java.util.Collections;

import static nl.zoostation.database.app.Constants.Parameters.SEARCH_FILTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valentinnastasi
 */
public class ProfileSearchControllerTest extends ContextAwareBaseControllerTest {

    private static final String ZS_NUMBER = "111";

    @Autowired
    private IProfileSearchService profileSearchService;

    @Autowired
    private MockHttpSession httpSession;

    @After
    public void tearDown() throws Exception {
        Mockito.reset(profileSearchService);
    }

    @Test
    public void testOpenHomePageEmptySearchFilter() throws Exception {
        ProfileSearchFormObject formObject = new ProfileSearchFormObject();
        formObject.setZsNumber(ZS_NUMBER);

        ProfileSearchFormWrapper formWrapper = new ProfileSearchFormWrapper();
        formWrapper.setForm(formObject);

        when(profileSearchService.prepareForm()).thenReturn(formWrapper);

        assertThat(httpSession.getAttribute(SEARCH_FILTER)).isNull();

        getMockMvc().perform(get("/").session(httpSession))
                .andExpect(view().name("/index"))
                .andExpect(model().attribute("languages", formWrapper.getProgrammingLanguages()))
                .andExpect(model().attribute("frameworks", formWrapper.getFrameworks()))
                .andExpect(model().attribute("companyTypes", formWrapper.getCompanyTypes()))
                .andExpect(model().attribute("contractTypes", formWrapper.getContractTypes()))
                .andExpect(model().attribute("rankTypes", formWrapper.getRankTypes()))
                .andExpect(model().attribute("roleTypes", formWrapper.getRoleTypes()))
                .andExpect(model().attribute("countries", formWrapper.getCountries()));

        assertThat(httpSession.getAttribute(SEARCH_FILTER)).isNotNull().isEqualToComparingFieldByField(formWrapper.getForm());
    }

    @Test
    public void testOpenHomePage() throws Exception {
        ProfileSearchFormObject formObject = new ProfileSearchFormObject();
        formObject.setZsNumber(ZS_NUMBER);

        ProfileSearchFormWrapper formWrapper = new ProfileSearchFormWrapper();
        formWrapper.setForm(new ProfileSearchFormObject());

        when(profileSearchService.prepareForm()).thenReturn(formWrapper);

        httpSession.setAttribute(SEARCH_FILTER, formObject);

        assertThat(httpSession.getAttribute(SEARCH_FILTER)).isNotNull().isEqualToComparingFieldByField(formObject);

        getMockMvc().perform(get("/").session(httpSession))
                .andExpect(view().name("/index"))
                .andExpect(model().attribute("languages", formWrapper.getProgrammingLanguages()))
                .andExpect(model().attribute("frameworks", formWrapper.getFrameworks()))
                .andExpect(model().attribute("companyTypes", formWrapper.getCompanyTypes()))
                .andExpect(model().attribute("contractTypes", formWrapper.getContractTypes()))
                .andExpect(model().attribute("rankTypes", formWrapper.getRankTypes()))
                .andExpect(model().attribute("roleTypes", formWrapper.getRoleTypes()))
                .andExpect(model().attribute("countries", formWrapper.getCountries()));

        assertThat(httpSession.getAttribute(SEARCH_FILTER)).isNotNull().isEqualToComparingFieldByField(formObject);
    }

    @Test
    public void testGetGridData() throws Exception {
        ArgumentCaptor<GridViewInputSpec> argumentCaptor = ArgumentCaptor.forClass(GridViewInputSpec.class);
        when(profileSearchService.getGridData(argumentCaptor.capture())).thenReturn(createGridViewOutputSpec());

        getMockMvc().perform(
                get("/profile/grid").session(httpSession)
                        .param("draw", "2")
                        .param("columns[0][data]", "id")
                        .param("columns[0][name]", "")
                        .param("columns[0][searchable]", "true")
                        .param("columns[0][orderable]", "true")
                        .param("columns[0][search][value]", "")
                        .param("columns[0][search][regex]", "false")
                        .param("order[0][column]", "0")
                        .param("order[0][dir]", "asc")
                        .param("start", "0")
                        .param("length", "10")
                        .param("search[value]", "")
                        .param("search[regex]", "false")
                        .param("searchFilter", "{\"zsNumber\":\"111\", \"visaNeeded\":\"true\", \"knownFrameworkIds\":[1, 2, 3]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.draw", is(2)))
                .andExpect(jsonPath("$.recordsTotal", is(1)))
                .andExpect(jsonPath("$.recordsFiltered", is(1)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].rank", is("jr")))
                .andExpect(jsonPath("$.data[0].mainProgrammingLanguage", is("Java")))
                .andExpect(jsonPath("$.data[0].secondProgrammingLanguage", is("C")))
                .andExpect(jsonPath("$.data[0].testRating", is(50)))
                .andExpect(jsonPath("$.error", isEmptyOrNullString()));

        assertThat(httpSession.getAttribute(SEARCH_FILTER)).isNotNull().is(new SearchCondition());

        GridViewInputSpec gridViewInputSpec = argumentCaptor.getValue();
        assertThat(gridViewInputSpec).isNotNull();
        assertThat(gridViewInputSpec.getExtras()).isNotNull().hasSize(1).hasEntrySatisfying(SEARCH_FILTER, new SearchCondition());
    }

    @Test
    public void testGridDataError() throws Exception {
        String message = "some message";
        when(profileSearchService.getGridData(any(GridViewInputSpec.class))).thenThrow(new RuntimeException(message));

        getMockMvc().perform(
                get("/profile/grid").session(httpSession)
                        .param("draw", "2")
                        .param("columns[0][data]", "id")
                        .param("columns[0][name]", "")
                        .param("columns[0][searchable]", "true")
                        .param("columns[0][orderable]", "true")
                        .param("columns[0][search][value]", "")
                        .param("columns[0][search][regex]", "false")
                        .param("order[0][column]", "0")
                        .param("order[0][dir]", "asc")
                        .param("start", "0")
                        .param("length", "10")
                        .param("search[value]", "")
                        .param("search[regex]", "false")
                        .param("searchFilter", "{\"zsNumber\":\"111\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.draw", is(2)))
                .andExpect(jsonPath("$.recordsTotal", is((Object) null)))
                .andExpect(jsonPath("$.recordsFiltered", is((Object) null)))
                .andExpect(jsonPath("$.data", is((Object) null)))
                .andExpect(jsonPath("$.error", is(message)));
    }

    private GridViewOutputSpec<ProfileGridRow> createGridViewOutputSpec() {
        ProfileGridRow profileGridRow = new ProfileGridRow(1L, "jr", "Java", "C", 50, "NL");
        GridViewOutputSpec<ProfileGridRow> gridViewOutputSpec = new GridViewOutputSpec<>();
        gridViewOutputSpec.setTotalRecords(1L);
        gridViewOutputSpec.setFilteredRecords(1L);
        gridViewOutputSpec.setRecords(Collections.singletonList(profileGridRow));
        return gridViewOutputSpec;
    }

    private class SearchCondition extends Condition<Object> {

        @Override
        public boolean matches(Object value) {
            ProfileSearchFormObject formObject = (ProfileSearchFormObject) value;
            return ZS_NUMBER.equals(formObject.getZsNumber());
        }
    }

}