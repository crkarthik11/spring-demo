package com.thinkxfactor.springdemo.entities;

import java.net.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long bookID;
    
    @Column(name = "isbn13")
    private long ISBN13;
    @Column(name = "img_url")
    private URL bookImageURL;
    @Column(name = "name")
    private String bookName;
    @Column(name = "author")
    private String bookAuthor;
    @Column(name = "publisher")
    private String bookPublisher;
    @Column(name = "price")
    private String bookPrice;
    @Column(name = "genre")
    private String bookGenre;

    
    public long getISBN13() {
        return ISBN13;
    }
    public void setISBN13(long iSBN13) {
        ISBN13 = iSBN13;
    }
    public URL getBookImageURL() {
        return bookImageURL;
    }
    public void setBookImageURL(URL bookImageURL) {
        this.bookImageURL = bookImageURL;
    }
    public long getBookID() {
        return bookID;
    }
    public void setBookID(long bookID) {
        this.bookID = bookID;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBookPublisher() {
        return bookPublisher;
    }
    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }
    public String getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
    public String getBookGenre() {
        return bookGenre;
    }
    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }
    
}
