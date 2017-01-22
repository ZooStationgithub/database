package nl.zoostation.database.web.controller;

import nl.zoostation.database.service.IProfileDetailsService;
import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author valentinnastasi
 */
public class ExceptionHandlingAdviceTest extends ContextAwareBaseControllerTest {

    @Autowired
    private IProfileDetailsService profileDetailsService;

    @After
    public void tearDown() throws Exception {
        Mockito.reset(profileDetailsService);
    }

    @Test
    public void testHandleAjaxException() throws Exception {
        final long id = 1L;
        final String message = "some message";

        when(profileDetailsService.getDetails(id)).thenThrow(new RuntimeException(message));

        getMockMvc().perform(
                get("/developer")
                        .header("X-Requested-With", "XMLHttpRequest")
                        .param("u", String.valueOf(id)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.httpCode", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
                .andExpect(jsonPath("$.exceptionClass", is(RuntimeException.class.getName())))
                .andExpect(jsonPath("$.message", containsString(message)));
    }

    @Test
    public void testHandleException() throws Exception {
        final long id = 1L;
        final String message = "some message";

        when(profileDetailsService.getDetails(id)).thenThrow(new RuntimeException(message));

        getMockMvc().perform(get("/developer").param("u", String.valueOf(id)))
                .andExpect(view().name("/error/error"))
                .andExpect(model().attributeExists("message", "stackTrace"))
                .andExpect(model().attribute("exceptionMessage", containsString(message)));
    }
}
