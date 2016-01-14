package com.karton.restbuck.user.web;

import com.karton.restbuck.user.ServiceUsers;
import com.karton.restbuck.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private ServiceUsers serviceUsers;

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers(){
        return serviceUsers.getUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    String postUser(String name){
        return serviceUsers.postUser(name);
    }

}
