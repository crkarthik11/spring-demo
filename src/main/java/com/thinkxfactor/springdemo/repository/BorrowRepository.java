package com.thinkxfactor.springdemo.repository;

import java.util.List;
import java.util.Optional;

import com.thinkxfactor.springdemo.relations.Borrow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BorrowRepository extends JpaRepository<Borrow, Long>{
    
    @Query("SELECT br.bid FROM Borrow br where br.sid = :SID")
    List<Optional<Long>> findBySid(@Param("SID") Long SID);

    @Query("SELECT br.sid FROM Borrow br where br.bid = :BID")
    List<Optional<Long>> findByBid(@Param("BID") Long BID);

    // two extra annotations are required for DML queries
    @Modifying
    @Transactional
    @Query("DELETE FROM Borrow br WHERE br.sid = :SID AND br.bid = :BID")
    void deleteBySidAndBid(@Param("SID") Long SID, @Param("BID") Long BID);
}
