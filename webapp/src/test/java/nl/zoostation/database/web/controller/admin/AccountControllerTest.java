package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.exception.DatabaseException;
import nl.zoostation.database.model.domain.AccountGroup;
import nl.zoostation.database.model.domain.SecurityRole;
import nl.zoostation.database.model.form.AccountFormObject;
import nl.zoostation.database.model.form.AccountFormWrapper;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IAccountFormService;
import nl.zoostation.database.service.IGridDataService;
import nl.zoostation.database.web.controller.ContextAwareBaseControllerTest;
import org.junit.After;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valentinnastasi
 */
public class AccountControllerTest extends ContextAwareBaseControllerTest {

    private static final long ID = 1L;
    private static final String LOGIN = "qq@qq.qq";
    private static final String PASSWORD = "password";
    private static final String GROUP_NAME = "group";
    private static final long GROUP_ID = 2L;
    private static final boolean ACTIVATED = true;
    private static final boolean ONLINE = false;
    private static final Instant CREATION_DATE = Instant.now();

    @Autowired
    private IGridDataService<AccountGridRow> accountGridDataService;

    @Autowired
    private IAccountFormService accountFormService;

    @After
    public void tearDown() throws Exception {
        Mockito.reset(accountGridDataService, accountFormService);
    }

    @Test
    public void testOpenTab() throws Exception {
        getMockMvc().perform(get("/admin/account/tab"))
                .andExpect(view().name("/admin/account/accountTab"));
    }

    @Test
    public void testGetGridData() throws Exception {
        AccountGridRow row = new AccountGridRow(ID, LOGIN, GROUP_NAME, CREATION_DATE, ACTIVATED, ONLINE);
        GridViewOutputSpec<AccountGridRow> gridViewOutputSpec = new GridViewOutputSpec<>(1L, 1L, Collections.singletonList(row));

        ArgumentCaptor<GridViewInputSpec> argumentCaptor = ArgumentCaptor.forClass(GridViewInputSpec.class);
        when(accountGridDataService.getGridData(argumentCaptor.capture())).thenReturn(gridViewOutputSpec);

        getMockMvc().perform(
                get("/admin/account/grid")
                        .param("draw", "2")
                        .param("columns[0][data]", "id")
                        .param("columns[0][name]", "")
                        .param("columns[0][searchable]", "true")
                        .param("columns[0][orderable]", "true")
                        .param("columns[0][search][value]", "")
                        .param("columns[0][search][regex]", "false")
                        .param("columns[1][data]", "login")
                        .param("columns[1][name]", "")
                        .param("columns[1][searchable]", "true")
                        .param("columns[1][orderable]", "true")
                        .param("columns[1][search][value]", "")
                        .param("columns[1][search][regex]", "false")
                        .param("columns[2][data]", "group")
                        .param("columns[2][name]", "")
                        .param("columns[2][searchable]", "true")
                        .param("columns[2][orderable]", "true")
                        .param("columns[2][search][value]", "")
                        .param("columns[2][search][regex]", "false")
                        .param("columns[3][data]", "creationDate")
                        .param("columns[3][name]", "")
                        .param("columns[3][searchable]", "true")
                        .param("columns[3][orderable]", "true")
                        .param("columns[3][search][value]", "")
                        .param("columns[3][search][regex]", "false")
                        .param("columns[4][data]", "activated")
                        .param("columns[4][name]", "")
                        .param("columns[4][searchable]", "true")
                        .param("columns[4][orderable]", "true")
                        .param("columns[4][search][value]", "")
                        .param("columns[4][search][regex]", "false")
                        .param("columns[5][data]", "online")
                        .param("columns[5][name]", "")
                        .param("columns[5][searchable]", "true")
                        .param("columns[5][orderable]", "true")
                        .param("columns[5][search][value]", "")
                        .param("columns[5][search][regex]", "false")
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
                .andExpect(jsonPath("$.data[0].login", is(LOGIN)))
                .andExpect(jsonPath("$.data[0].group", is(GROUP_NAME)))
//                .andExpect(jsonPath("$.data[0].creationDate", is(CREATION_DATE)))
                .andExpect(jsonPath("$.data[0].activated", is(ACTIVATED)))
                .andExpect(jsonPath("$.data[0].online", is(ONLINE)))
                .andExpect(jsonPath("$.error", is((Object) null)));

        GridViewInputSpec gridViewInputSpec = argumentCaptor.getValue();
        assertThat(gridViewInputSpec).isNotNull();
        assertThat(gridViewInputSpec.getGlobalFilter()).isEqualTo("aqwe");
        assertThat(gridViewInputSpec.getFilterableColumns()).hasSize(6);
    }

    @Test
    public void testOpenForm() throws Exception {
        AccountFormObject formObject = new AccountFormObject();
        List<AccountGroup> groups = Arrays.asList(new AccountGroup(1L, "Administrator", SecurityRole.ROLE_ADMIN));
        AccountFormWrapper formWrapper = new AccountFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setAccountGroups(groups);

        when(accountFormService.prepareForm(Optional.empty())).thenReturn(formWrapper);

        getMockMvc().perform(get("/admin/account/form"))
                .andExpect(view().name("/admin/account/accountForm"))
                .andExpect(model().attribute("account", is(formObject)))
                .andExpect(model().attribute("groups", is(groups)));
    }

    @Test
    public void testSavePasswordMismatch() throws Exception {
        AccountFormObject formObject = new AccountFormObject();
        List<AccountGroup> groups = Collections.singletonList(new AccountGroup(1L, "Administrator", SecurityRole.ROLE_ADMIN));
        AccountFormWrapper formWrapper = new AccountFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setAccountGroups(groups);

        when(accountFormService.prepareForm(Optional.empty())).thenReturn(formWrapper);

        getMockMvc().perform(
                post("/admin/account")
                        .param("email", LOGIN)
                        .param("password", PASSWORD)
                        .param("confirmPassword", "fghjk")
                        .param("groupId", String.valueOf(GROUP_ID)))
                .andExpect(view().name("/admin/account/accountForm"))
                .andExpect(model().attributeExists("account", "groups"))
                .andExpect(model().attributeErrorCount("account", 1))
                .andExpect(model().attributeHasFieldErrorCode("account", "confirmPassword", "NoMatch.account.confirmPassword"));
    }

    @Test
    public void testSaveValidationError() throws Exception {
        AccountFormObject formObject = new AccountFormObject();
        List<AccountGroup> groups = Collections.singletonList(new AccountGroup(1L, "Administrator", SecurityRole.ROLE_ADMIN));
        AccountFormWrapper formWrapper = new AccountFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setAccountGroups(groups);

        when(accountFormService.prepareForm(Optional.empty())).thenReturn(formWrapper);

        getMockMvc().perform(
                post("/admin/account")
                        .param("email", "werjk")
                        .param("password", PASSWORD)
                        .param("confirmPassword", PASSWORD)
                        .param("groupId", ""))
                .andExpect(view().name("/admin/account/accountForm"))
                .andExpect(model().attributeExists("account", "groups"))
                .andExpect(model().attributeErrorCount("account", 2))
                .andExpect(model().attributeHasFieldErrors("account", "email", "groupId"));
    }

    @Test
    public void testSaveEmailInUse() throws Exception {
        AccountFormObject formObject = new AccountFormObject();
        List<AccountGroup> groups = Collections.singletonList(new AccountGroup(1L, "Administrator", SecurityRole.ROLE_ADMIN));
        AccountFormWrapper formWrapper = new AccountFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setAccountGroups(groups);

        when(accountFormService.prepareForm(Optional.empty())).thenReturn(formWrapper);
        when(accountFormService.save(any(AccountFormObject.class))).thenThrow(PersistenceException.class);

        getMockMvc().perform(
                post("/admin/account")
                        .param("email", LOGIN)
                        .param("password", PASSWORD)
                        .param("confirmPassword", PASSWORD)
                        .param("groupId", String.valueOf(GROUP_ID)))
                .andExpect(view().name("/admin/account/accountForm"))
                .andExpect(model().attributeExists("account", "groups"))
                .andExpect(model().attributeErrorCount("account", 1))
                .andExpect(model().attributeHasFieldErrorCode("account", "email", "Unique.account.email"));
    }

    @Test
    public void testSave() throws Exception {
        getMockMvc().perform(
                post("/admin/account")
                        .param("email", LOGIN)
                        .param("password", PASSWORD)
                        .param("confirmPassword", PASSWORD)
                        .param("groupId", String.valueOf(GROUP_ID)))
                .andExpect(status().isCreated())
                .andExpect(content().string(""));

        ArgumentCaptor<AccountFormObject> argumentCaptor = ArgumentCaptor.forClass(AccountFormObject.class);
        verify(accountFormService).save(argumentCaptor.capture());

        AccountFormObject formObject = argumentCaptor.getValue();
        assertThat(formObject).isNotNull();
        assertThat(formObject.getEmail()).isEqualTo(LOGIN);
        assertThat(formObject.getPassword()).isEqualTo(PASSWORD);
        assertThat(formObject.getConfirmPassword()).isEqualTo(PASSWORD);
        assertThat(formObject.getGroupId()).isEqualTo(GROUP_ID);
    }

    @Test
    public void testDeleteObjectInUse() throws Exception {
        doThrow(DataIntegrityViolationException.class).when(accountFormService).delete(ID);

        getMockMvc().perform(delete("/admin/account/" + ID))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.httpCode", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .andExpect(jsonPath("$.exceptionClass", is(DatabaseException.class.getName())))
                .andExpect(jsonPath("$.message", is(not(empty()))));
    }

    @Test
    public void testDelete() throws Exception {
        getMockMvc().perform(delete("/admin/account/" + ID))
                .andExpect(status().isNoContent());

        verify(accountFormService).delete(ID);
    }
}