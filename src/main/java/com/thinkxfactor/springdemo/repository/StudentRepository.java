package com.thinkxfactor.springdemo.repository;

import com.thinkxfactor.springdemo.entities.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
