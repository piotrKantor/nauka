package org.karton.restbuck.user.web

import com.karton.restbuck.Main
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class UserAccountControllerTest extends Specification{

    def NAME ="highlander";
    def PASSWORD ="&password=there_can_be_only_one";
    def GET="/users";
    def ADD="/users?name=";
    def LOGIN="/login?username=highlander&password=there_can_be_only_one";
    def TASK_CREATE ="/tasks?name=";
    def ASSIGN_TASK ="/users/1/task?name=";
    def TASK_NAME="zadanie1";

    @Autowired
    WebApplicationContext webCtx;

    private MockMvc mockMvc;

    def setup() {
        mockMvc= MockMvcBuilders.webAppContextSetup(webCtx).build();
    }


    def shouldGetUserItPosted(){
        given:
        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken("userName", null, "ROLE_USER")
        SecurityContextHolder.getContext().setAuthentication(authenticationToken)
        mockMvc.perform(post(ADD+NAME+PASSWORD));

        when:
        mockMvc.perform(post(LOGIN));
        MvcResult result= mockMvc.perform(get(GET)).andReturn();
        String content=result.getResponse().getContentAsString();

        then:
        content.contains(NAME)
    }

    def shouldGetCorrectTaskForUser(){
        given:
        mockMvc.perform(post(ADD+NAME));
        mockMvc.perform(post(TASK_CREATE+TASK_NAME));
        mockMvc.perform(put(ASSIGN_TASK +TASK_NAME));

        when:
        MvcResult result= mockMvc.perform(get(GET)).andReturn();
        String content=result.getResponse().getContentAsString();

        then:
        content.contains(TASK_NAME)
    }
}
