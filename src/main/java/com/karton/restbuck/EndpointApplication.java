package com.karton.restbuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping(path = "users")
public class EndpointApplication {

    static List<User> users=new ArrayList<>();

    @RequestMapping(path = "get", method = RequestMethod.GET)
    List<User> getUsers(){
        return users;
    }

    @RequestMapping(path = "add")
    String addUser(String name){
        users.add(User.builder().name(name).build());
        return users.get(users.size()-1).getName();
    }

    public static void main(String[] args) {
        SpringApplication.run(EndpointApplication.class, args);
    }

}
