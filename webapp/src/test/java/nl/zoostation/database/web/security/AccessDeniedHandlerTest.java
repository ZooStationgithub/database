package nl.zoostation.database.web.security;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author valentinnastasi
 */
@WithMockUser(username = "A", password = "B", roles = "COMPANY")
public class AccessDeniedHandlerTest extends BaseSecurityTest {

    @Test
    public void testAccessDenied() throws Exception {
        getMockMvc().perform(get("/admin"))
                .andExpect(forwardedUrl("/accessdenied"));
    }

    @Test
    public void testAccessDeniedAjax() throws Exception {
        getMockMvc().perform(get("/admin").header("X-Requested-With", "XMLHttpRequest"))
                .andExpect(jsonPath("$.httpCode", is(HttpStatus.FORBIDDEN.value())));
    }
}
