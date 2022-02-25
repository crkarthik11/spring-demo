package com.thinkxfactor.springdemo.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.thinkxfactor.springdemo.entities.Student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    private Map<String, Student> studentMap = new HashMap<>();

    // read
    @GetMapping("/getStudentByUUID")
    public Student getStudentByUUID(@RequestParam String studentID) {
        return studentMap.get(studentID);
    }

    // read
    @GetMapping("/getAllStudent")
    public Map<String, Student> getAllStudent() {
        return this.studentMap;
    }

    // delete
    @GetMapping("/deleteAllStudent") 
    public String deleteAllStudent(@RequestParam boolean delete) {
        if (delete) {
            studentMap.clear();
        }
        return "Student database deleted successfuly";
    }

    // create
    @PostMapping("/addStudent")
    public String addStudent(@RequestBody Student student) {
        student.setStudentID('s' + UUID.randomUUID().toString());
        studentMap.put(student.getStudentID(), student);
        return "student added successfuly with ID: " + student.getStudentID();
    }

}
