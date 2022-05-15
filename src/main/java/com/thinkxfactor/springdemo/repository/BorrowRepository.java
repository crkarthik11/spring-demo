package com.thinkxfactor.springdemo.repository;

import com.thinkxfactor.springdemo.entities.Borrow;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long>{
    
}
