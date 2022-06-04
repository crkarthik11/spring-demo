package com.thinkxfactor.springdemo.APIs;

import com.thinkxfactor.springdemo.models.Book;
import com.thinkxfactor.springdemo.repo.BookRepo;

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
@RequestMapping("/book")
@CrossOrigin
public class BookAPI {

    @Autowired
    BookRepo bookRepository;

    // create
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    // read
    @GetMapping("/readAll")
    public ResponseEntity<?> readAll() {
        return ResponseEntity.ok(bookRepository.findAll());
    }
    @GetMapping("/readById")
    public ResponseEntity<?> readById(@RequestParam Long id) {
        if(!bookRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            return ResponseEntity.ok(bookRepository.findById(id));
        }
    }
}