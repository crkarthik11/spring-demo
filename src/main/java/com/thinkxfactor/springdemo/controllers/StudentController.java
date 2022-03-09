package com.thinkxfactor.springdemo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.thinkxfactor.springdemo.entity.Student;
import com.thinkxfactor.springdemo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    List<Student> students = new ArrayList<>();

    // Get student by roll no :: GET with request parmas
    // Add Student :: POST
    // Get Student list :: GET
    // Delete student by Id :: GET

    @GetMapping("/echo")
    public String sayEcho() {
        return "Echo from student controller";
    }

    // Path Parm
    // Request Param

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return "Greetings " + name + "!!";
    }

    @GetMapping("/getAll")
    public List<Student> getAll() {
        List<Student> students = studentRepository.findAll();
        return students;

        // return studentRepository.findAll();;
    }

    @DeleteMapping("/delete/{id}")
    public void greet(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

    @GetMapping("/createStudent")
    public HashMap<String, Object> createStudent(@RequestParam String name,
            @RequestParam int age, @RequestParam String gender) {
        HashMap<String, Object> respMap = new HashMap<>();
        respMap.put("name", name);
        respMap.put("age", age);
        respMap.put("gender", gender);
        // JAP:: save data to DB

        // students.add(respMap);
        return respMap;
    }

    @PostMapping("/addStudent")
    public Student addStudents(@RequestBody Student student) {
        System.out.println("Student object received !!");
        Student persistStudent = studentRepository.save(student);
        System.out.println("Student object saved !!");
        // System.out.println("Total elements in array : " + students.size());
        return persistStudent;
    }

    // @PutMapping("/updateStudent")
    // public List<Student> updateStudent(@RequestBody Student student) {
    // // Save the object to database, ID gets generated
    // student.setId(UUID.randomUUID().toString());
    // students.add(student);
    // System.out.println("Total elements in array : " + students.size());
    // return students;
    // }

    @GetMapping("/getByUsername")
    public Student getByUsername(@RequestParam String username) {
        System.out.println("====Inside Get mapping of getByUsername ");
        System.out.println("====Query with username  :" + username);
        Student persistStudent = studentRepository.findByUsername(username);
        System.out.println("====Query result  :" + persistStudent.getId());
        return persistStudent;
    }

    // @GetMapping("/getByUsernameandPassword")
    // public Student getByUsernameandPassword(@RequestParam String username,
    // @RequestParam String password) {
    // Student persistStudent = null;
    // System.out.println("====Inside Get mapping of getByUsernameandPassword ");
    // System.out.println("====Query with username :" + username + " and pssword " +
    // password);
    // try {
    // persistStudent = studentRepository.findByUsernameAndPassword(username,
    // password);
    // System.out.println("====Query result :" + persistStudent.getId());
    // } catch (Exception e) {
    // // TODO: handle exception
    // System.err.println("Student with username :" + username + " and pssword " +
    // password + "Not found");
    // }

    // return persistStudent;

    // }

    @GetMapping("/getByUsernameandPassword")
    public Student getByUsernameandPassword(@RequestParam String username, @RequestParam String password) {
        Student stu = null;
        System.out.println("====Inside Get mapping of getByUsernameandPassword ");
        System.out.println("====Query with username  :" + username + " and pssword " + password);

        Optional<Student> persistStudent = studentRepository.findByUsernameAndPassword(username, password);
        if (persistStudent.isPresent()) {
            System.out.println("====Query result  :" + persistStudent.get().getId());
            return persistStudent.get();
        }

        return stu;

    }

}
