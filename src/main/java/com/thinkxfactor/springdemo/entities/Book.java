package com.thinkxfactor.springdemo.entities;

public class Book {
    private long ISBN10;
    private long ISBN13;
    private String bookLibraryCopyID;
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private String bookPrice;
    private String bookGenre;
    private String bookSubGenre;

    // getters and setters
    public long getISBN10() {
        return ISBN10;
    }
    public void setISBN10(long iSBN10) {
        ISBN10 = iSBN10;
    }
    public long getISBN13() {
        return ISBN13;
    }
    public void setISBN13(long iSBN13) {
        ISBN13 = iSBN13;
    }
    public String getBookLibraryCopyID() {
        return bookLibraryCopyID;
    }
    public void setBookLibraryCopyID(String bookLibraryCopyID) {
        this.bookLibraryCopyID = bookLibraryCopyID;
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
    public String getBookSubGenre() {
        return bookSubGenre;
    }
    public void setBookSubGenre(String bookSubGenre) {
        this.bookSubGenre = bookSubGenre;
    }

    
}
