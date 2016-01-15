package com.karton.restbuck.user.web;

import com.karton.restbuck.user.User;
import com.karton.restbuck.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    @NonNull private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    User postUser(String name){
        return userService.createUser(name);
    }

}
