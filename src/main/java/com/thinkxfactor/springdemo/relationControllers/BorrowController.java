package com.thinkxfactor.springdemo.relationControllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thinkxfactor.springdemo.relations.Borrow;
import com.thinkxfactor.springdemo.repository.BookRepository;
import com.thinkxfactor.springdemo.repository.BorrowRepository;
import com.thinkxfactor.springdemo.repository.StudentRepository;
import com.thinkxfactor.springdemo.services.BookQtyMgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    BookQtyMgr bookQtyMgr;

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
                // check if book available (i.e book qty > 0)
                else if(bookQtyMgr.getBookQty(bid) == 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }

            // performing actions
            for(Long bid : bidSet) {

                // decrement book qty by 1 when borrowed
                bookQtyMgr.bookQtyDec(bid);

                borrowRepository.save(new Borrow(sid, bid));
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }

    }
    
    // getBorrowDetails

    // getAllBorrowDetails
    @GetMapping("/allBorrowDetails")
    public ResponseEntity<?> getAllBorrowDetails() {
        return ResponseEntity.ok(borrowRepository.findAll());
    }

    // getBorrowedBooks
    @GetMapping("/borrowedBooks")
    public ResponseEntity<?> getBorrowedBooks(@RequestParam Long sid) {
        if(!studentRepository.existsById(sid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.ok(borrowRepository.findBySid(sid));
        }
    }

    // getStudentsWhoBorrowed
    @GetMapping("/borrowedBy")
    public ResponseEntity<?> getBorrowedBy(@RequestParam Long bid) {
        if(!bookRepository.existsById(bid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.ok(borrowRepository.findByBid(bid));
        }
    }

    // ---- return controller ---- TODO
}
