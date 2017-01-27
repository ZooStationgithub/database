package nl.zoostation.database.web.controller;

import nl.zoostation.database.app.config.IntegrationConfig;
import nl.zoostation.database.app.config.MvcConfig;
import nl.zoostation.database.app.config.PropertiesConfig;
import nl.zoostation.database.app.config.TestServiceConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author valentinnastasi
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {PropertiesConfig.class, IntegrationConfig.class, MvcConfig.class, TestServiceConfig.class})
public abstract class ContextAwareBaseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected MockMvc getMockMvc() {
        return mockMvc;
    }

}
