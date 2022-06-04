package com.thinkxfactor.springdemo.issuanceAPIs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.thinkxfactor.springdemo.issuances.Issuance;
import com.thinkxfactor.springdemo.mgr.BookQtyMgr;
import com.thinkxfactor.springdemo.models.Book;
import com.thinkxfactor.springdemo.models.Student;
import com.thinkxfactor.springdemo.repo.BookRepo;
import com.thinkxfactor.springdemo.repo.IssuanceRepo;
import com.thinkxfactor.springdemo.repo.StudentRepo;

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
@RequestMapping("/issuance")
@CrossOrigin
public class IssueAPI {

    @Autowired
    StudentRepo studentRepository;

    @Autowired
    BookRepo bookRepository;

    @Autowired
    IssuanceRepo issuanceRepository;

    @Autowired
    BookQtyMgr bookQtyMgr;

    @PostMapping("/issueBooks")
    public ResponseEntity<?> issuanceBooks(@RequestParam Long sid, @RequestBody List<Long> bidList) {

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

                // decrement book qty by 1 when issuanceed
                bookQtyMgr.bookQtyDec(bid);

                issuanceRepository.save(new Issuance(sid, bid));
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }

    }

    // getissuanceDetails

    // getAllissuanceDetails
    @GetMapping("/allIssuanceDetails")
    public ResponseEntity<?> getAllissuanceDetails() {

        // store the records in a HashMap
        Map<Optional<Student>, Set<Optional<Book>>> records = new HashMap<>();

        // HashSet for the Student's id (We used set because id(s) are unique)
        Set<Optional<Long>> sidList = new HashSet<>(issuanceRepository.findAllSid());

        // iterate for each student who issuanceed
        for (Optional<Long> sid : sidList) {

            if (sid.isPresent()) {
                if (studentRepository.existsById(sid.get())) {

                    Set<Optional<Book>> bookSet = new HashSet<>();

                    // iterate for each book the student issuanceed
                    for (Optional<Long> bid : issuanceRepository.findBySid(sid.get())) {

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

    // getissuanceedBooks
    @GetMapping("/issuedBooks")
    public ResponseEntity<?> getissuanceedBooks(@RequestParam Long sid) {
        if (!studentRepository.existsById(sid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            // to store the book's details
            Set<Optional<Book>> bookSet = new HashSet<>();

            // list of book's id
            List<Optional<Long>> bidList = issuanceRepository.findBySid(sid);

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

    // getStudentsWhoissuanceed
    @GetMapping("/issuedBy")
    public ResponseEntity<?> getissuanceedBy(@RequestParam Long bid) {
        if (!bookRepository.existsById(bid)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {

            // to store the student's details
            Set<Optional<Student>> studentSet = new HashSet<>();

            // list of student's id
            List<Optional<Long>> sidList = issuanceRepository.findByBid(bid);

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
