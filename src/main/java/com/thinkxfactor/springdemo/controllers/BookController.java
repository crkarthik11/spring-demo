package com.thinkxfactor.springdemo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.thinkxfactor.springdemo.entities.Book;
import com.thinkxfactor.springdemo.repository.BookRepository;

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

    // read
    @GetMapping("/getBookByID")
    public Object getBookByID(@RequestParam Long bookID) {
        if (bookRepository.findById(bookID) == null) {
            return "Book not found!!";
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
    public String deleteAllBook(@PathVariable boolean delete) {
        if (delete) {
            bookRepository.deleteAll();
        }
        return "Book database deleted successfuly!";
    }

    // delete
    @DeleteMapping("deleteBook")
    public String deleteBook(@RequestParam Long bookID) {
        if (bookRepository.findById(bookID) == null) {
            return "Book not found!!";
        }
        bookRepository.deleteById(bookID);
        return bookID + " deleted successfuly!!";
    }

    // create
    @PostMapping("/addBook")
    public Object addBook(@RequestBody Book book) {
        System.out.println("Book object received!!");
        Book persistantBook = bookRepository.save(book);
        System.out.println("Book object saved!!");
        return persistantBook;
    }

    // update
    @PutMapping("/updateBook")
    public Object updateBook(@RequestBody Book book) {
        if (book.getBookID() == 0) {
            return "Book not found!!";
        }
        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }
}
