package com.thinkxfactor.springdemo.controllers;

import java.lang.reflect.Method;
// import java.util.HashMap;
import java.util.List;
// import java.util.Map;
import java.util.Optional;
// import java.util.UUID;

import com.thinkxfactor.springdemo.entities.Admin;
import com.thinkxfactor.springdemo.repository.AdminRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(this.getClass()); 

    // read
    @GetMapping("/getAdminByID")
    public Optional<Admin> getAdminByID(@RequestParam Long adminID) {
        if (!adminRepository.existsById(adminID)) {
            log.warn("Object doesn't exist");
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
    public Optional<Admin> deleteAllAdmin(@PathVariable boolean delete) {
        log.warn("Database will be deleted");
        if (delete) {
            adminRepository.deleteAll();
            log.info("Database deleted successfully");
        }
        else {
            log.warn("Operation abandoned");
        }
        return null;
    }

    // delete
    @DeleteMapping("/deleteAdmin")
    public Optional<Admin> deleteAdmin(@RequestParam Long adminID) {
        if (adminRepository.existsById(adminID)) {
            log.warn("Object doesn't exist");
        }
        adminRepository.deleteById(adminID);
        log.info(adminID + " Deleted successfully");
        return null;
    }

    // create
    @PostMapping("/addAdmin")
    public Admin addAdmin(@RequestBody Admin admin) {
        log.info("Object recieved");
        Admin savedAdmin = adminRepository.save(admin);
        log.info(savedAdmin.getAdminID() + " Saved");
        return savedAdmin;
    }

    // update
    @PutMapping("/updateAdmin")
    public Admin updateAdmin(@RequestBody Admin admin) {
        if (!adminRepository.existsById(admin.getAdminID())) {
            log.warn("Object dosen't exist");
            return null;
        }
        log.info("Object recieved");
        Admin updatedAdmin = adminRepository.save(admin);
        log.info(updatedAdmin.getAdminID() + " Updated");
        return updatedAdmin;
    }

    // find by admin name 
    @GetMapping("/findByName")
    public List<Optional<Admin>> findByName(@RequestParam String name) {
        return adminRepository.findByAdminName(name);
    }

    // find by age between
    @GetMapping("/findAdminByAgeBetween")
    public List<Optional<Admin>> findAdminByAgeBetween(@RequestParam int startAge, @RequestParam int endAge) {
        if (startAge <= endAge) {
            return adminRepository.findByAgeBetween(startAge, endAge);
        }
        else {
            log.warn("Lower bound should be less than or equal to upper bound");
            return null;
        }
    }
}
