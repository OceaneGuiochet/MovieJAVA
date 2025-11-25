package com.ndroc.rocmovies.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndroc.rocmovies.entity.Borrow;
import com.ndroc.rocmovies.entity.Customer;
import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.repository.BorrowRepository;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    public List<Movie> getBorrowedMoviesByCustomerId(Integer customerId) {
        return borrowRepository.findByCustomerCustomerId(customerId)
                               .stream()
                               .map(Borrow::getMovie)
                               .collect(Collectors.toList());
    }

    public List<Customer> getCustomersByBorrowedMovieStyle(Style style) {
        return borrowRepository.findDistinctByMovieStyle(style)
                               .stream()
                               .map(Borrow::getCustomer)
                               .distinct()
                               .collect(Collectors.toList());
    }
}
