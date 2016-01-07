package com.karton.restbuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
public class EndpointApplication {

    @RequestMapping("/")
    String getUser(){
        String message="There are no users in this application yet";
        return message;
    }

    public static void main(String[] args) {
        SpringApplication.run(EndpointApplication.class, args);
    }

}
