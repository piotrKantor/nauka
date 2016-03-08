package org.karton.restbuck.task.web

import com.karton.restbuck.Main
import com.karton.restbuck.task.Task
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringApplicationConfiguration(classes = Main.class)
@IntegrationTest
@WebAppConfiguration
class TaskControllerIntegrationTest extends Specification{

    def NAME ="highlander"
    def GET="http://localhost:8080/tasks"
    def ADD="http://localhost:8080/tasks?name="

    def shouldGetTheSameTasksItPosted(){
        given:
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.postForLocation(ADD+NAME, String.class);

        when:
        ResponseEntity<List<Task>> tasksResponse =
                restTemplate.exchange(GET, HttpMethod.GET, null, new ParameterizedTypeReference<List<Task>>() {});
        List<Task> tasks = tasksResponse.getBody();
        String actual=tasks.get(0).getName();

        then:
        NAME.equals(actual)
    }

}
