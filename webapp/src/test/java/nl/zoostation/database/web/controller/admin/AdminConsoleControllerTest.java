package nl.zoostation.database.web.controller.admin;

import nl.zoostation.database.web.controller.StandaloneBaseControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author valentinnastasi
 */
public class AdminConsoleControllerTest extends StandaloneBaseControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new AdminConsoleController()).build();
    }

    @Test
    public void testOpenAdminConsolePage() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(view().name("/admin/adminConsole"));
    }
}