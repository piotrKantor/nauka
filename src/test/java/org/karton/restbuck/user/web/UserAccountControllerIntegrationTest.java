package org.karton.restbuck.user.web;


import com.karton.restbuck.Main;
import com.karton.restbuck.user.UserAccount;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@IntegrationTest
@WebAppConfiguration
public class UserAccountControllerIntegrationTest {

    private final String BASE ="http://localhost:8080/";
    private final String NAME ="highlander";
    private final String PASSWORD ="&password=there_can_be_only_one";
    private final String USERS="users";
    private final String ADD="?name=";
    private final String LOGIN="login?username=";

    @Test
    public void shouldGetTheSameUserNameItPosted() throws Exception{
        //given
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.postForLocation(BASE+ USERS +ADD+NAME+PASSWORD, String.class);
        ResponseEntity responseEntity=restTemplate.postForEntity(BASE+LOGIN+NAME+PASSWORD, null, String.class);
        String cookie=responseEntity.getHeaders().get("Set-Cookie").get(0);
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.add("Cookie", cookie);
        HttpEntity requestEntity=new HttpEntity(requestHeaders);

        //when
        ResponseEntity<List<UserAccount>> usersResponse =
                restTemplate.exchange(BASE+ USERS, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<UserAccount>>() {});
        List<UserAccount> userAccounts = usersResponse.getBody();
        String actual= userAccounts.get(0).getName();

        //then
        Assert.assertEquals(NAME, actual);
    }

}
