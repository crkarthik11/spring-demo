package com.thinkxfactor.springdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thinkxfactor.springdemo.models.Admin;

public interface AdminRepo extends JpaRepository<Admin,Long>{
    
    
}
