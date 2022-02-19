package com.thinkxfactor.springdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

import com.thinkxfactor.springdemo.entity.Student;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    private ArrayList<HashMap> generateStudents() {
        ArrayList<HashMap> students = new ArrayList<HashMap>();
        students.add(new Student("John", "Computer Science", 1, 20).getData());
        students.add(new Student("Jane", "Computer Science", 2, 21).getData());
        students.add(new Student("Jack", "Computer Science", 3, 22).getData());
        students.add(new Student("Jill", "Computer Science", 4, 23).getData());
        students.add(new Student("Joe", "Computer Science", 5, 24).getData());
        return students;
    }

    @GetMapping("/get-all-student")
    public ArrayList<HashMap> getAllStudent() {
        return generateStudents();
    }

    @GetMapping("/get-student-by-roll/{roll}")
    public Student getStudentByRoll(@PathVariable int roll) {
        ArrayList<HashMap> students = generateStudents();
        for (HashMap student : students) {
            if (student.get("rollNo").equals(roll)) {
                return new Student(student.get("name").toString(), student.get("department").toString(),
                        Integer.parseInt(student.get("rollNo").toString()),
                        Integer.parseInt(student.get("age").toString()));
            }
        }
        return null;
    }
}
