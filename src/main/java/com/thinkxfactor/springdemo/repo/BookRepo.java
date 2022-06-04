package com.thinkxfactor.springdemo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.thinkxfactor.springdemo.models.Book;

public interface BookRepo extends JpaRepository<Book,Long>{
    
    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.qty = b.qty + 1 WHERE b.id = :BID")
    void incQtyByOne(@Param("BID") Long BID);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.qty = b.qty + :VAL WHERE b.id = :BID")
    void incQty(@Param("BID") Long BID, @Param("VAL") Integer VAL);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.qty = b.qty - 1 WHERE b.id = :BID")
    void decQtyByOne(@Param("BID") Long BID);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.qty = b.qty - :VAL WHERE b.id = :BID")
    void decQty(@Param("BID") Long BID, @Param("VAL") Integer VAL);

    @Query("SELECT b.qty FROM Book b WHERE b.id = :BID")
    Integer getQty(@Param("BID") Long BID);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.qty = 0 WHERE b.qty < 0")
    void qtyHandler();

}
