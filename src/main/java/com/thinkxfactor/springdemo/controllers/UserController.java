package com.thinkxfactor.springdemo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    int count = 0;

    @GetMapping("/")
    public String error() {
        return "ERROR from User Controller";
    }

    @GetMapping("/counter")
    public String count() {
        count = count + 1;
        return "The counter value is : " + count;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "This is a new server running!!";

    }

    @GetMapping("/hello2")
    public Map<String, String> sayHello2() {
        Map<String, String> respMap = new HashMap<>();
        respMap.put("name", "raj");
        respMap.put("age", "25");
        respMap.put("dept", "CSE");
        return respMap;

    }

}
