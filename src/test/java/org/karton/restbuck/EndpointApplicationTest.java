package org.karton.restbuck;


import com.karton.restbuck.EndpointApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EndpointApplication.class)
@IntegrationTest
@WebAppConfiguration
public class EndpointApplicationTest {

    final String NAME ="highlander";
    final String GET="http://localhost:8080/users/get";
    final String ADD="http://localhost:8080/users/add?name="+ NAME;

    @Test
    public void testGetUser() throws Exception{
        new RestTemplate().getForObject(ADD, String.class);
        String result=new RestTemplate().getForObject(GET, String.class);
        String expected=new StringBuilder()
                .append("[{\"name\":\"")
                .append(NAME)
                .append("\"}]")
                .toString();
        Assert.assertEquals(expected, result);
    }

}
