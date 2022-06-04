package com.thinkxfactor.springdemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.thinkxfactor.springdemo.issuances.Issuance;

public interface IssuanceRepo extends JpaRepository<Issuance, Long>{
    
    @Query("SELECT isu.bid FROM Issuance isu where isu.sid = :SID")
    List<Optional<Long>> findBySid(@Param("SID") Long SID);

    @Query("SELECT isu.sid FROM Issuance isu")
    List<Optional<Long>> findAllSid();

    @Query("SELECT isu.sid FROM Issuance isu where isu.bid = :BID")
    List<Optional<Long>> findByBid(@Param("BID") Long BID);

    // two extra annotations are required for DML queries
    @Modifying
    @Transactional
    @Query("DELETE FROM Issuance isu WHERE isu.sid = :SID AND isu.bid = :BID")
    void deleteBySidAndBid(@Param("SID") Long SID, @Param("BID") Long BID);
}
