package com.thinkxfactor.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkxfactor.springdemo.models.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

    
}
