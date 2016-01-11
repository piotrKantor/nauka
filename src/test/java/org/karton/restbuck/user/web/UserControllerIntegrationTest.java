package org.karton.restbuck.user.web;


import com.karton.restbuck.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@IntegrationTest
@WebAppConfiguration
public class UserControllerIntegrationTest {

    final String NAME ="highlander";
    final String GET="http://localhost:8080/users";
    final String ADD="http://localhost:8080/users?name=";

    @Test
    public void testGetUser() throws Exception{
        new RestTemplate().postForLocation(ADD+NAME, String.class);
        String result=new RestTemplate().getForObject(GET, String.class);
        String expected=new StringBuilder()
                .append("[{\"name\":\"")
                .append(NAME)
                .append("\"}]")
                .toString();
        Assert.assertEquals(expected, result);
    }

}
