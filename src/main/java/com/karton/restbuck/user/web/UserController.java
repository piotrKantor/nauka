package com.karton.restbuck.user.web;

import com.karton.restbuck.user.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "users")
@EnableAutoConfiguration
public class UserController {

    List<User> users=new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers(){
        return users;
    }

    @RequestMapping(method = RequestMethod.POST)
    String addUser(String name){
        users.add(User.builder().name(name).build());
        return users.get(users.size()-1).getName();
    }

}
