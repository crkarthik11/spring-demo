package com.thinkxfactor.springdemo.repository;

import com.thinkxfactor.springdemo.entities.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    
}
