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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class UserControllerTest {

    private final String NAME ="highlander";
    private final String GET="/users";
    private final String ADD="/users?name=";
    private final String TASK_CREATE ="/tasks?name=";
    private final String ASSIGN_TASK ="/users/1/task?name=";
    private final String TASK_NAME="zadanie1";

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

    @Test
    public void shouldGetCorrectTaskForUser() throws Exception{
        //given
        mockMvc.perform(post(ADD+NAME));
        mockMvc.perform(post(TASK_CREATE+TASK_NAME));
        mockMvc.perform(put(ASSIGN_TASK +TASK_NAME));

        //when
        MvcResult result= mockMvc.perform(get(GET)).andReturn();

        //then
        String content=result.getResponse().getContentAsString();
        assertThat(content).containsPattern(TASK_NAME);
    }

}
