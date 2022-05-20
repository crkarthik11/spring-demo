package com.thinkxfactor.springdemo.relationControllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.thinkxfactor.springdemo.entities.Book;
import com.thinkxfactor.springdemo.entities.Student;
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
        if (!studentRepository.existsById(sid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            // checking existence of books
            Set<Long> bidSet = new HashSet<>(bidList);
            for (Long bid : bidSet) {
                if (!bookRepository.existsById(bid)) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
                // check if book available (i.e book qty > 0)
                else if (bookQtyMgr.getBookQty(bid) == 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }

            // performing actions
            for (Long bid : bidSet) {

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

        // store the records in a HashMap
        Map<Optional<Student>, Set<Optional<Book>>> records = new HashMap<>();

        // HashSet for the Student's id (We used set because id(s) are unique)
        Set<Optional<Long>> sidList = new HashSet<>(borrowRepository.findAllSid());

        // iterate for each student who borrowed
        for (Optional<Long> sid : sidList) {

            if (sid.isPresent()) {
                if (studentRepository.existsById(sid.get())) {

                    Set<Optional<Book>> bookSet = new HashSet<>();

                    // iterate for each book the student borrowed
                    for (Optional<Long> bid : borrowRepository.findBySid(sid.get())) {

                        if (bid.isPresent()) {

                            if (bookRepository.existsById(bid.get())) {
                                bookSet.add(bookRepository.findById(bid.get()));
                            } else {
                                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                            }
                        }
                    }

                    // add <Student, List<Book>> to Map records
                    records.put(studentRepository.findById(sid.get()), bookSet);

                } else {

                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }
        }

        return ResponseEntity.ok(records);
    }

    // getBorrowedBooks
    @GetMapping("/borrowedBooks")
    public ResponseEntity<?> getBorrowedBooks(@RequestParam Long sid) {
        if (!studentRepository.existsById(sid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            // to store the book's details
            Set<Optional<Book>> bookSet = new HashSet<>();

            // list of book's id
            List<Optional<Long>> bidList = borrowRepository.findBySid(sid);

            // add book object(s) according to the id(s)
            for (Optional<Long> id : bidList) {
                if (id.isPresent() && bookRepository.existsById(id.get())) {
                    bookSet.add(bookRepository.findById(id.get()));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

            return ResponseEntity.ok(bookSet);
        }
    }

    // getStudentsWhoBorrowed
    @GetMapping("/borrowedBy")
    public ResponseEntity<?> getBorrowedBy(@RequestParam Long bid) {
        if (!bookRepository.existsById(bid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            // to store the student's details
            Set<Optional<Student>> studentSet = new HashSet<>();

            // list of student's id
            List<Optional<Long>> sidList = borrowRepository.findByBid(bid);

            // add student object(s) according to the id(s)
            for (Optional<Long> id : sidList) {
                if (id.isPresent() && studentRepository.existsById(id.get())) {
                    studentSet.add(studentRepository.findById(id.get()));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }

            return ResponseEntity.ok(studentSet);
        }
    }

    // ---- return controller ---- TODO
}
