package com.thinkxfactor.springdemo.controllers;

// import java.util.HashMap;
import java.util.List;
// import java.util.Map;
// import java.util.UUID;
import java.util.Optional;

import com.thinkxfactor.springdemo.entities.Book;
import com.thinkxfactor.springdemo.repository.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    private Logger log = LoggerFactory.getLogger(this.getClass()); 

    // read
    @GetMapping("/getBookByID")
    public Optional<Book> getBookByID(@RequestParam Long bookID) {
        if (!bookRepository.existsById(bookID)) {
            log.warn("Object dosen't exist");
        }
        return bookRepository.findById(bookID);
    }

    // readAll
    @GetMapping("/getAllBook")
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    // deleteAll
    @DeleteMapping("/deleteAllBook/{delete}")
    public Optional<Book> deleteAllBook(@PathVariable boolean delete) {
        log.warn("Database will be deleted");
        if (delete) {
            bookRepository.deleteAll();
            log.info("Database deleted successfully");
        }
        else {
            log.warn("Operation abandoned");
        }
        return null;
    }

    // delete
    @DeleteMapping("deleteBook")
    public Optional<Book> deleteBook(@RequestParam Long bookID) {
        log.warn("Object will be deleted");
        if (!bookRepository.existsById(bookID)) {
            log.warn("Object not found");
        }
        bookRepository.deleteById(bookID);
        log.info(bookID + " Deleted successfully");
        return null;
    }

    // create
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        log.info("Object recieved");
        Book savedBook = bookRepository.save(book);
        log.info(savedBook.getBookID() + " Saved");
        return savedBook;
    }

    // update
    @PutMapping("/updateBook")
    public Book updateBook(@RequestBody Book book) {
        if (!bookRepository.existsById(book.getBookID())) {
            log.warn("Object dosen't exist");
            return null;
        }
        log.info("Object recieved");
        Book updatedBook = bookRepository.save(book);
        log.info(updatedBook.getBookID() + " Updated");
        return updatedBook;
    }
}
