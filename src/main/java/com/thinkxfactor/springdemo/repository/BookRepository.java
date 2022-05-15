package com.thinkxfactor.springdemo.repository;


import com.thinkxfactor.springdemo.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>{
    
    
}
