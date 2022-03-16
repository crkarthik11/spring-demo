package com.thinkxfactor.springdemo.repository;

import java.util.List;
import java.util.Optional;

import com.thinkxfactor.springdemo.entities.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    
    List<Optional<Admin>> findByAdminName(String adminName);

    Optional<Admin> findByAdminUsername(String adminUsername);

    List<Optional<Admin>> findByAdminUsernameAndAdminPassword(String adminUsername, String adminPassword);

    @Query(value = "SELECT * FROM tbl_admin WHERE AGE >= ?1 AND AGE <= ?2", nativeQuery = true)
    List<Optional<Admin>> findByAgeBetween(int startAge, int endAge);
}
