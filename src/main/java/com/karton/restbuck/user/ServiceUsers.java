package com.karton.restbuck.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUsers {

    List<User> users=new ArrayList<>();

    public List<User> getUsers(){
        return users;
    }

    public String postUser(String name){
        users.add(User.builder().name(name).build());
        return users.get(users.size()-1).getName();
    }
}
