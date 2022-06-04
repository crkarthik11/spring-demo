package com.thinkxfactor.springdemo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Long isbn;

    @Column(nullable = false)
    private Integer qty;

    public Book(Long id, Long isbn) {
        this.id = id;
        this.isbn = isbn;
        this.qty = 0;
    }

}
