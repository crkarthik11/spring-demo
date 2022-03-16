package com.thinkxfactor.springdemo.repository;

import java.util.List;
import java.util.Optional;

import com.thinkxfactor.springdemo.entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT student FROM Student student WHERE student.studentDepartment = : DEPT")
    Optional<List<Student>> getStudentByDepartment(String DEPT);
}
