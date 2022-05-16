package com.thinkxfactor.springdemo.relationControllers;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thinkxfactor.springdemo.repository.BookRepository;
import com.thinkxfactor.springdemo.repository.BorrowRepository;
import com.thinkxfactor.springdemo.repository.StudentRepository;
import com.thinkxfactor.springdemo.services.URI_MGR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/return")
@CrossOrigin
public class ReturnController {
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    URI_MGR uMgr;

    // TODO
    // 1. return books
    @DeleteMapping("/returnBooks")
    public ResponseEntity<?> returnBooks(@RequestParam Long sid, @RequestBody List<Long> bidList) {

        // check existence of student
        if(!studentRepository.existsById(sid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            // check existence of books
            Set<Long> bidSet = new HashSet<>(bidList);
            for(Long bid : bidSet) {
                if(!bookRepository.existsById(bid)) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

            // perform actions

            for(Long bid : bidSet) {
                borrowRepository.deleteBySidAndBid(sid, bid);
            }

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
    }

    // 2. show books to return (redirect to similar method /borrow/borrowedBooks)
    @GetMapping("/booksToReturn")
    public ResponseEntity<?> booksToReturn(@RequestParam Long sid) {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(uMgr.BASE_URL + "/borrow/borrowedBooks?sid=" + sid)).build();
    }

}