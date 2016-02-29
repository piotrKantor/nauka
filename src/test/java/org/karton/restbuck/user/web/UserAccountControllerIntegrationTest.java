package org.karton.restbuck.user.web;


import com.karton.restbuck.Main;
import com.karton.restbuck.user.UserAccount;
import org.junit.Assert;
import org.junit.Ignore;
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
public class UserAccountControllerIntegrationTest {

    private final String NAME ="highlander";
    private final String PASSWORD ="&password=there_can_be_only_one";
    private final String GET="http://localhost:8080/users";
    private final String ADD="http://localhost:8080/users?name=";
    private final String LOGIN="http://localhost:8080/login?username=highlander&password=there_can_be_only_one";

    @Ignore //TODO Refactor this and other tests in next commit
    @Test
    public void shoulgGetTheSameUserNameItPosted() throws Exception{
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.postForLocation(ADD+NAME+PASSWORD, String.class);
        restTemplate.postForLocation(LOGIN, String.class);
        ResponseEntity<List<UserAccount>> usersResponse =
                restTemplate.exchange(GET, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserAccount>>() {});
        List<UserAccount> userAccounts = usersResponse.getBody();
        String actual= userAccounts.get(0).getName();
        Assert.assertEquals(NAME, actual);
    }

}
