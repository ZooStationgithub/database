package nl.zoostation.database.web.controller.registration;

import nl.zoostation.database.service.IAccountManagementService;
import nl.zoostation.database.web.controller.StandaloneBaseControllerTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author valentinnastasi
 */
public class AccountActivationControllerTest extends StandaloneBaseControllerTest {

    private static final long ID = 1L;

    @Mock
    private IAccountManagementService accountActivationService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new AccountActivationController(accountActivationService))
                .build();
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(accountActivationService);
    }

    @Test
    public void testActivateAccount() throws Exception {
        mockMvc.perform(get("/account/activate").param("u", String.valueOf(ID)))
                .andExpect(view().name("/accountActivated"));
        verify(accountActivationService).activate(eq(ID));
    }

    @Test
    public void testResendActivationLink() throws Exception {
        mockMvc.perform(get("/admin/account/activate/resend/" + ID))
                .andExpect(status().isAccepted());
        verify(accountActivationService).resendActivation(eq(ID));
    }
}