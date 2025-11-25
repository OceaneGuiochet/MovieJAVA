package com.ndroc.rocmovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ndroc.rocmovies.entity.Customer;
import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.service.BorrowService;

@RestController
@RequestMapping("/borrows")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/customer/{customerId}")
    public List<Movie> getBorrowedMoviesByCustomer(@PathVariable Integer customerId) {
        return borrowService.getBorrowedMoviesByCustomerId(customerId);
    }

    @GetMapping("/customers/by-style/{styleId}")
    public List<Customer> getCustomersByBorrowedMovieStyle(@PathVariable Integer styleId) {
        Style style = new Style();
        style.setStyleId(styleId);
        return borrowService.getCustomersByBorrowedMovieStyle(style);
    }
}
