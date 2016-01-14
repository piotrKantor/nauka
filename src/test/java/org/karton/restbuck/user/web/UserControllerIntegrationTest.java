package org.karton.restbuck.user.web;


import com.karton.restbuck.Main;
import com.karton.restbuck.user.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@IntegrationTest
@WebAppConfiguration
public class UserControllerIntegrationTest {

    private final String NAME ="highlander";
    private final String GET="http://localhost:8080/users";
    private final String ADD="http://localhost:8080/users?name=";

    @Test
    public void testGetUser() throws Exception{
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.postForLocation(ADD+NAME, String.class);
        ResponseEntity<List<User>> usersResponse =
                restTemplate.exchange(GET, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> users = usersResponse.getBody();
        String actual=users.get(0).getName();
        Assert.assertEquals(NAME, actual);
    }

}
