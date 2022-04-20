package com.thinkxfactor.springdemo.repository;

import java.util.List;
import java.util.Optional;

import com.thinkxfactor.springdemo.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long>{
    
    List<Optional<Book>> findByISBN13(long isbn13);
}
