package com.thinkxfactor.springdemo.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkxfactor.springdemo.repo.BookRepo;


@Service
public class BookQtyMgr {

    @Autowired
    BookRepo bookRepository;

    /**
     * Increment operations
     * @param bid
     * @param val
     */
    
    // increment book qty by a value
    public void bookQtyInc(Long bid, Integer val) {

        if(!bookRepository.existsById(bid)) {
            return;
        }
        
        bookRepository.incQty(bid, val);

    }

    // increment book qty by 1
    public void bookQtyInc(Long bid) {

        if(!bookRepository.existsById(bid)) {
            return;
        }
        
        bookRepository.incQtyByOne(bid);

    }

    /**
     * Decrement operations
     * @param bid
     * @return
     */

    // decrement qty by val 
    public void bookQtyDec(Long bid, Integer val) { 

        if(!bookRepository.existsById(bid)) {
            return;
        }

        Integer qty = bookRepository.getQty(bid);

        if(qty - val < 0) {
            return;
        }

        bookRepository.decQty(bid, val);
    }

    // decrement qty by 1 
    public void bookQtyDec(Long bid) { 

        if(!bookRepository.existsById(bid)) {
            return;
        }

        Integer qty = bookRepository.getQty(bid);

        if(qty == 0) {
            return;
        }

        bookRepository.decQtyByOne(bid);
    }

    // check book qty
    public Integer getBookQty(Long bid) {
        
        if(!bookRepository.existsById(bid)) {
            return null;
        }
        else {
            return bookRepository.getQty(bid);
        }
    }

    /**
     * handle negetive values in qty column
     * set them to 0 if found negetive
     */
    public void handleNegQty() {
        bookRepository.qtyHandler();
    }
}
