package nl.zoostation.database.web.controller.profile;

import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.IProfileDetailsService;
import nl.zoostation.database.web.controller.StandaloneBaseControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valentinnastasi
 */
public class ProfileDetailsControllerTest extends StandaloneBaseControllerTest {

    private static final long ID = 1L;

    @Mock
    private IProfileDetailsService profileDetailsService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProfileDetailsController(profileDetailsService)).build();
    }

    @Test
    public void testOpenDetailsPage() throws Exception {
        ProfileView profileView = new ProfileView();
        when(profileDetailsService.getDetails(ID)).thenReturn(profileView);

        mockMvc.perform(get("/developer").param("u", String.valueOf(ID)))
                .andExpect(view().name("/developerDetails"))
                .andExpect(model().attribute("profile", profileView));
    }

    @Test
    public void testRequestMoreInfo() throws Exception {
        mockMvc.perform(get("/developer/info").param("u", String.valueOf(ID)))
                .andExpect(status().isAccepted());
        verify(profileDetailsService).requestMoreInfo(eq(ID));
    }
}