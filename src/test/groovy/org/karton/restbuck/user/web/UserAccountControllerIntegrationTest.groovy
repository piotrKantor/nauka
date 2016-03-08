package org.karton.restbuck.user.web

import com.karton.restbuck.Main
import com.karton.restbuck.user.UserAccount
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

@SpringApplicationConfiguration(classes = Main.class)
@IntegrationTest
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserAccountControllerIntegrationTest extends Specification{

    def BASE ="http://localhost:8080/"
    def NAME ="highlander"
    def PASSWORD ="&password=there_can_be_only_one"
    def USERS="users"
    def ADD="?name="
    def LOGIN="login?username="
    RestTemplate restTemplate=new RestTemplate();

    def shouldGetTheSameUserNameItPosted(){
        given:
        restTemplate.postForLocation(BASE+ USERS +ADD+NAME+PASSWORD, String.class);
        HttpEntity requestEntity = getLoggedUserHttpEntity(restTemplate);

        when:
        ResponseEntity<List<UserAccount>> usersResponse =
                restTemplate.exchange(BASE+ USERS, HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<UserAccount>>() {});
        List<UserAccount> userAccounts = usersResponse.getBody();
        String actual= userAccounts.get(0).getName();

        then:
        NAME.equals(actual)
    }

    public void shouldThrownHttp409IfUserToBeAddedAlreadyExists(){
        when:
        restTemplate.postForLocation(BASE+ USERS +ADD+NAME+PASSWORD, String.class);
        restTemplate.postForLocation(BASE+ USERS +ADD+NAME+PASSWORD, String.class);

        then:
        HttpClientErrorException ex=thrown()
        ex.message.equals("409 Conflict")
    }

    public void shouldThrowHttp401IfNotLoggedIn(){
        given:
        restTemplate.postForLocation(BASE+ USERS +ADD+NAME+PASSWORD, String.class);

        when:
        ResponseEntity<List<UserAccount>> usersResponse =
                restTemplate.exchange(BASE+ USERS, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserAccount>>() {});

        then:
        HttpClientErrorException ex=thrown()
        ex.message.equals("401 Unauthorized")
    }

    private HttpEntity getLoggedUserHttpEntity(RestTemplate restTemplate) {
        ResponseEntity responseEntity=restTemplate.postForEntity(BASE+LOGIN+NAME+PASSWORD, null, String.class);
        String cookie=responseEntity.getHeaders().get("Set-Cookie").get(0);
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.add("Cookie", cookie);
        return new HttpEntity(requestHeaders);
    }

}
