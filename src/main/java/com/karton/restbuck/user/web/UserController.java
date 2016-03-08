package com.karton.restbuck.user.web;

import com.karton.restbuck.user.UserAccount;
import com.karton.restbuck.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    @NonNull private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    List<UserAccount> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> postUser(String name, String password){
        if(StringUtils.isEmpty(name)||StringUtils.isEmpty(password)){
            return new ResponseEntity<String>("Name and password cannot be null",HttpStatus.BAD_REQUEST);
        }
        try {
            UserAccount account = userService.createUser(name, password);
            return new ResponseEntity<UserAccount>(account, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("User with given name already exists",HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/task")
    UserAccount addTask(@PathVariable("id") Long id, String name){
        return userService.addTask(id, name);
    }

}
