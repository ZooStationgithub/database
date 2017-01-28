package nl.zoostation.database.web.security;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;

/**
 * @author valentinnastasi
 */
public class UrlAccessTest extends BaseSecurityTest {

    @Test
    public void testAccessLoginAnonymous() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/login").with(anonymous()));

    }
}
