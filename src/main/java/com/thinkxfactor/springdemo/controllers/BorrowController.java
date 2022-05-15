package com.thinkxfactor.springdemo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thinkxfactor.springdemo.entities.Borrow;
import com.thinkxfactor.springdemo.repository.BookRepository;
import com.thinkxfactor.springdemo.repository.BorrowRepository;
import com.thinkxfactor.springdemo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrow")
@CrossOrigin
public class BorrowController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowRepository borrowRepository;

    @PostMapping("/borrowBooks")
    public ResponseEntity<?> borrowBooks(@RequestParam Long sid, @RequestBody List<Long> bidList) {
        
        // checking existence of students
        if(!studentRepository.existsById(sid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            // checking existence of books
            Set<Long> bidSet = new HashSet<>(bidList);
            for(Long bid : bidSet) {
                if(!bookRepository.existsById(bid)) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

            // performing actions
            for(Long bid : bidSet) {
                borrowRepository.save(new Borrow(sid, bid));
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }

    }
    
    // getBorrowDetails

    // getAllBorrowDetails

    // getBorrowedBooks

    // getStudentsWhoBorrowed

    // ---- return controller ----
}
