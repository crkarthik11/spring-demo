package com.thinkxfactor.springdemo.controllers;

import java.util.List;
import java.util.Optional;
// import java.util.logging.Logger;

import com.thinkxfactor.springdemo.entities.Student;
import com.thinkxfactor.springdemo.repository.StudentRepository;

// import org.apache.commons.logging.Log;
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
@RequestMapping("/student")
public class StudentController {

    private Logger log = LoggerFactory.getLogger(this.getClass()); 
    // Logger.getLogger("StudentController");

    @Autowired
    private StudentRepository studentRepository;

    // read
    @GetMapping("/getStudentByID")
    public Optional<Student> getStudentByID(@RequestParam Long studentID) {
        if (!studentRepository.existsById(studentID)) {
            log.warn("Object dosen't exist");
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
    public Optional<Student> deleteAllStudent(@PathVariable boolean delete) {
        log.warn("Database will be deleted");
        if (delete) {
            studentRepository.deleteAll();
            log.info("Database deleted successfully");
        } else {
            log.warn("Operation abandoned");
        }
        return null;
    }

    // delete
    @DeleteMapping("/deleteStudent")
    public Optional<Student> deleteStudent(@RequestParam Long studentID) {
        log.warn("Object will be deleted");
        if (!studentRepository.existsById(studentID)) {
            log.warn("Object not found");
        }
        studentRepository.deleteById(studentID);
        log.info(studentID + " Deleted successfully");
        return null;
    }

    // create
    @PostMapping("/addStudent")
    public Student addStudent(@RequestBody Student student) {

        log.info("Object recieved");
        Student savedStudent = studentRepository.save(student);
        log.info(savedStudent.getStudentID() + " Saved");
        return savedStudent;
    }

    // update
    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {
        if (!studentRepository.existsById(student.getStudentID())) {
            log.warn("Object dosen't exist");
            return null;
        }
        log.info("Object recieved");
        Student updatedStudent = studentRepository.save(student);
        log.info(updatedStudent.getStudentID() + " Updated");
        return updatedStudent;
    }

}
