package com.karton.restbuck.user.web;

import com.karton.restbuck.user.ServiceUsers;
import com.karton.restbuck.user.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private ServiceUsers serviceUsers=new ServiceUsers();

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers(){
        return serviceUsers.getUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    String postUser(String name){
        return serviceUsers.postUser(name);
    }

}
