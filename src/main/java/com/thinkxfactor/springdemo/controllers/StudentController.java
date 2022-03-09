package com.thinkxfactor.springdemo.controllers;

import java.util.List;

import com.thinkxfactor.springdemo.entities.Student;
import com.thinkxfactor.springdemo.repository.StudentRepository;

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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // read
    @GetMapping("/getStudentByUUID")
    public Object getStudentByUUID(@RequestParam Long studentID) {
        if(studentRepository.findById(studentID)==null){
            return "Student not found!!";
        }
        return studentRepository.findById(studentID);
    }

    // readAll
    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    // deleteAll
    @DeleteMapping("/deleteAllStudent/{delete}") 
    public String deleteAllStudent(@PathVariable boolean delete) {
        if (delete) {
            studentRepository.deleteAll();
        }
        return "Student database deleted successfuly!";
    }

    // delete
    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentID){
        if(studentRepository.findById(studentID)==null){
            return "Student not found!!";
        }
        studentRepository.deleteById(studentID);
        return studentID+" deleted successfuly!!";
    }

    // create
    @PostMapping("/addStudent")
    public Object addStudent(@RequestBody Student student) {

        System.out.println("Student object received!!");
        Student persistantStudent=studentRepository.save(student);
        System.out.println("Student object saved!!");
        return persistantStudent;
    }

    // update
    @PutMapping("/updateStudent")
    public Object updateStudent(@RequestBody Student student) {
        if(student.getStudentID()==0){
            return "Student not found!!";
        }
        Student updatedStudent=studentRepository.save(student);
        return updatedStudent;
    }


}
