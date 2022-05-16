package com.thinkxfactor.springdemo.relations;

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
public class Borrow {
    
    @Id
    @GeneratedValue
    private Long id;

    private Long sid;

    private Long bid;

    
    public Borrow(Long sid, Long bid) {
        this.sid = sid;
        this.bid = bid;
    }

    

}
