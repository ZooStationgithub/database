package nl.zoostation.database.web.controller.registration;

import nl.zoostation.database.web.controller.ContextAwareBaseControllerTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author valentinnastasi
 */
public class AuthorizationControllerTest extends ContextAwareBaseControllerTest {

    @Test
    public void testOpenLoginPage() throws Exception {
        getMockMvc().perform(get("/login")).andExpect(view().name("/login"));
    }

    @Test
    public void testOpenAccessDeniedPage() throws Exception {
        getMockMvc().perform(get("/accessdenied")).andExpect(view().name("/error/accessDenied"));
    }
}