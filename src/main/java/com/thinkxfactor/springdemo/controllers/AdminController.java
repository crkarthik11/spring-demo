package com.thinkxfactor.springdemo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.thinkxfactor.springdemo.entities.Admin;
import com.thinkxfactor.springdemo.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    // read
    @GetMapping("/getAdminByUUID")
    public Object getAdminByUUID(@RequestParam Long adminID) {
        if(adminRepository.findById(adminID)==null){
            return "Admin not found!!";
        }
        return adminRepository.findById(adminID);
    }

    // readAll
    @GetMapping("getAllAdmin") 
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    } 

    // deleteAll
    @DeleteMapping("/deleteAllAdmin/{delete}") 
    public String deleteAllAdmin(@PathVariable boolean delete) {
        if (delete) {
            adminRepository.deleteAll();
        }
        return "Student database deleted successfuly!";
    }

    // delete
    @DeleteMapping("/deleteAdmin")
    public String deleteAdmin(@RequestParam Long adminID) {
        if(adminRepository.findById(adminID)==null){
            return "Student not found!!";
        }
        adminRepository.deleteById(adminID);
        return adminID+" deleted successfuly!!";
    }

    // create
    @PostMapping("/addAdmin") 
    public Object addAdmin(@RequestBody Admin admin) {
        System.out.println("Admin object received!!");
        Admin persistantAdmin=adminRepository.save(admin);
        System.out.println("Admin object saved!!");
        return persistantAdmin;
    }

    // update
    @PutMapping("/updateAdmin")
    public Object updateAdmin(@RequestBody Admin admin) {
        if (admin.getAdminID()==0) {
            return "Admin not found!!";
        }
        Admin updatedAdmin=adminRepository.save(admin);
        return updatedAdmin;
    }
}
