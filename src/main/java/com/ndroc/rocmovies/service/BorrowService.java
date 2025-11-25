package com.ndroc.rocmovies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndroc.rocmovies.entity.Borrow;
import com.ndroc.rocmovies.repository.BorrowRepository;

@Service
public class BorrowService {

     @Autowired
    private BorrowRepository borrowRepository;

    public Borrow saveBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }
}
