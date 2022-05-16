package com.thinkxfactor.springdemo.testControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public String tester() {
        return "Add your test controllers here";
    }
}
