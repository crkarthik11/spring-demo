package com.thinkxfactor.springdemo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.thinkxfactor.springdemo.entity.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Map<String, Object> sayHello2() {
        Map<String, Object> respMap = new HashMap<>();
        respMap.put("name", "raj");
        respMap.put("age", Integer.valueOf(24));
        respMap.put("dept", "CSE");
        respMap.put("active", true);
        return respMap;

    }

    // @GetMapping("/getUser")
    // public User getUser() {

    // User us1 = new User();
    // us1.setName("Raj");
    // us1.setDepartment("CSE");
    // us1.setAge(25);
    // return us1;
    // }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        User us1 = new User();
        us1.setName("Raj");
        us1.setDepartment("CSE");
        us1.setAge(25);

        users.add(us1);

        User us2 = new User();
        us2.setName("Satwik");
        us2.setDepartment("IT");
        us2.setAge(21);

        users.add(us2);

        return users;

    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        System.out.println("hELLO Satwik");
        return "Hello " + name + "!!";
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam String name,
            @RequestParam(name = "dept") String department, @RequestParam int age) {

        User user = new User();
        user.setName(name);
        user.setDepartment(department);
        user.setAge(age);
        return user;

    }

    @GetMapping("/getUserByName")
    public User getUserByName(@RequestParam String name) {

        User user = new User();
        user.setName(name);

        return user;

    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user) {
        user.setId(UUID.randomUUID().toString());
        // DB op
        return user;

    }

}
