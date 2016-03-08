package org.karton.restbuck.task.web

import com.karton.restbuck.Main
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
class TaskControllerTest extends Specification{

    def NAME ="highlander"
    def GET="/tasks"
    def ADD="/tasks?name="

    @Autowired
    WebApplicationContext webCtx;

    private MockMvc mockMvc;

    def setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webCtx).build()
    }

    def shouldGetTheSameTaskNameItCreatedByPost(){
        given:
        mockMvc.perform(post(ADD+NAME));

        when:
        MvcResult result= mockMvc.perform(get(GET)).andReturn();
        String content=result.getResponse().getContentAsString();

        then:
        content.contains(NAME)
    }
}
