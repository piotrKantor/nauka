package com.karton.restbuck.user.web;

import com.karton.restbuck.user.UserAccount;
import com.karton.restbuck.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
    List<UserAccount> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<UserAccount> postUser(String name, String password){
        if(name==null||password==null){
            return new ResponseEntity<UserAccount>(HttpStatus.BAD_REQUEST);
        }
        UserAccount account=userService.createUser(name, password);
        return new ResponseEntity<UserAccount>(account, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/task")
    UserAccount addTask(@PathVariable("id") Long id, String name){
        return userService.addTask(id, name);
    }

}
