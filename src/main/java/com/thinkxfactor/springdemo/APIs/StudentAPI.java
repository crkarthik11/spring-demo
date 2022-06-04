package com.thinkxfactor.springdemo.APIs;

import com.thinkxfactor.springdemo.models.Student;
import com.thinkxfactor.springdemo.repo.StudentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentAPI {

    @Autowired
    StudentRepo studentRepository;

    // create
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    // read
    @GetMapping("/readAll")
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok(studentRepository.findAll());
    }
    @GetMapping("/readById")
    public ResponseEntity<?> readById(@RequestParam Long id) {
        if(!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.ok(studentRepository.findById(id));
        }
    }
}
