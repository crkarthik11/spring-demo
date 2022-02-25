package com.thinkxfactor.springdemo.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.thinkxfactor.springdemo.entities.Book;

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
    Map<String, Book> bookMap = new HashMap<>();

    // read
    @GetMapping("/getBookByUUID")
    public Book getBookByUUID(@RequestParam String bookUUID) {
        return bookMap.get(bookUUID);
    }

    // read
    @GetMapping("/getAllBook") 
    public Map<String, Book> getAllBook() {
        return this.bookMap;
    } 

    // delete
    @GetMapping("/deleteAllBook") 
    public String deleteAllBook(@RequestParam boolean delete) {
        if (delete) {
            bookMap.clear();
        }
        return "Book database deleted successfuly";
    }

    // create
    @PostMapping("/addBook") 
    public String addBook(@RequestBody Book book) {
        book.setBookLibraryCopyID('b' + UUID.randomUUID().toString());
        bookMap.put(book.getBookLibraryCopyID(), book);
        return "book added successfuly with ID: " + book.getBookLibraryCopyID();
    }

    // update
    @PutMapping("/updateBook") 
    public String updateBook(@RequestBody Book book, @PathVariable String bookLibraryCopyID) {
        bookMap.put(bookLibraryCopyID, book);
        return "Book with ID: " + bookLibraryCopyID + " updated successfully";
    }
}
