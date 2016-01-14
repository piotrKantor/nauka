package org.karton.restbuck.user.web;

import com.karton.restbuck.Main;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class UserControllerTest {

    final String NAME ="highlander";
    final String GET="http://localhost:8080/users";
    final String ADD="http://localhost:8080/users?name=";

    @Autowired
    WebApplicationContext webCtx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc= MockMvcBuilders.webAppContextSetup(webCtx).build();
    }

    @Test
    public void shouldGetCorrectUser() throws Exception {
        mockMvc.perform(post(ADD+NAME));
        MvcResult result= mockMvc.perform(get(GET)).andReturn();
        String content=result.getResponse().getContentAsString();
        assertThat(content).containsPattern(NAME);
    }

}
