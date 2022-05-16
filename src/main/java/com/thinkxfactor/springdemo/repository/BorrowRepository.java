package com.thinkxfactor.springdemo.repository;

import java.util.List;
import java.util.Optional;

import com.thinkxfactor.springdemo.relations.Borrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BorrowRepository extends JpaRepository<Borrow, Long>{
    
    @Query("SELECT br.bid FROM Borrow br where br.sid = :SID")
    List<Optional<Long>> findBySid(@Param("SID") Long SID);
}
