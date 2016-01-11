package org.karton.restbuck;

import com.karton.restbuck.EndpointApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EndpointApplication.class)
@WebAppConfiguration
public class EndpointApplicationTest {

    final String NAME ="highlander";
    final String GET="http://localhost:8080/users/get";
    final String ADD="http://localhost:8080/users/add?name="+ NAME;

    @Autowired
    WebApplicationContext webCtx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc= MockMvcBuilders.webAppContextSetup(webCtx).build();
    }

    @Test
    public void shouldGetCorrectUser() throws Exception {
        mockMvc.perform(get(ADD));
        MvcResult result= mockMvc.perform(get(GET)).andReturn();
        String actual=result.getResponse().getContentAsString();
        String expected=new StringBuilder()
                .append("[{\"name\":\"")
                .append(NAME)
                .append("\"}]")
                .toString();
        Assert.assertEquals(expected, actual);
    }

}
