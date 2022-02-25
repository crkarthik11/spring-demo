package com.thinkxfactor.springdemo.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.thinkxfactor.springdemo.entities.Admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    Map<String, Admin> adminMap = new HashMap<>();

    // read
    @GetMapping("/getAdminByUUID")
    public Admin getAdminByUUID(@RequestParam String adminID) {
        return adminMap.get(adminID);
    }

    // read
    @GetMapping("getAllAdmin") 
    public Map<String, Admin> getAllAdmin() {
        return this.adminMap;
    } 

    // delete
    @GetMapping("/deleteAllAdmin")
    public String deleteAllStudent(@RequestParam boolean delete) {
        if (delete) {
            adminMap.clear();
        }
        return "Admin database deleted successfuly";
    }

    // create
    @PostMapping("/addAdmin") 
    public String addAdmin(@RequestBody Admin admin) {
        admin.setAdminID('a' + UUID.randomUUID().toString());
        adminMap.put(admin.getAdminID(), admin);
        return "admin added successfuly with ID: " + admin.getAdminID();
    }
}
