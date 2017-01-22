package nl.zoostation.database.web.controller.profile;

import nl.zoostation.database.model.form.ProfileFormObject;
import nl.zoostation.database.model.form.ProfileFormWrapper;
import nl.zoostation.database.service.IFormService;
import nl.zoostation.database.web.controller.StandaloneBaseControllerTest;
import nl.zoostation.database.web.util.WebUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valentinnastasi
 */
public class ProfileManagementControllerTest extends StandaloneBaseControllerTest {

    private static final long ID = 1L;

    @Mock
    private IFormService<?, Long, ProfileFormObject, ProfileFormWrapper> profileFormService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProfileManagementController(profileFormService)).build();
    }

    @Test
    public void testOpenEditPage() throws Exception {
        ProfileFormObject formObject = new ProfileFormObject();
        ProfileFormWrapper formWrapper = new ProfileFormWrapper();
        formWrapper.setForm(formObject);

        when(profileFormService.prepareForm(Optional.of(ID))).thenReturn(formWrapper);

        mockMvc.perform(get("/developer/edit").param("u", String.valueOf(ID)))
                .andExpect(view().name("/developerForm"))
                .andExpect(model().attribute("profile", formObject))
                .andExpect(model().attribute("languages", formWrapper.getProgrammingLanguages()))
                .andExpect(model().attribute("frameworks", formWrapper.getFrameworks()))
                .andExpect(model().attribute("companyTypes", formWrapper.getCompanyTypes()))
                .andExpect(model().attribute("contractTypes", formWrapper.getContractTypes()))
                .andExpect(model().attribute("rankTypes", formWrapper.getRankTypes()))
                .andExpect(model().attribute("roleTypes", formWrapper.getRoleTypes()))
                .andExpect(model().attribute("countries", formWrapper.getCountries()));
    }

    @Test
    public void testSave() throws Exception {
        ProfileFormObject formObject = new ProfileFormObject();

        mockMvc.perform(post("/developer/edit").contentType(MediaType.APPLICATION_JSON).content(WebUtils.toJson(formObject)))
                .andExpect(status().isCreated());

        ArgumentCaptor<ProfileFormObject> argumentCaptor = ArgumentCaptor.forClass(ProfileFormObject.class);
        verify(profileFormService).save(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue()).isNotNull().isEqualToComparingFieldByField(formObject);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/developer").param("u", String.valueOf(ID)))
                .andExpect(status().isNoContent());

        verify(profileFormService).delete(eq(ID));
    }
}