package com.ndroc.rocmovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ndroc.rocmovies.entity.Borrow;
import com.ndroc.rocmovies.entity.Customer;
import com.ndroc.rocmovies.service.BorrowService;
import com.ndroc.rocmovies.service.CustomerService;

@RestController
public class CustomerController {

    private final BorrowService borrowService;

    @Autowired
    private CustomerService customerService;

    CustomerController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/customers/{id}/borrows")
    public ResponseEntity<List<Borrow>> getBorrowsByCustomer(@PathVariable Integer id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer.getBorrows());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/customers/{id}/borrows")
    public ResponseEntity<Borrow> addBorrowForCustomer(@PathVariable Integer id, @RequestBody Borrow borrow) {
        try {
            Customer customer = customerService.getCustomerById(id);
            borrow.setCustomer(customer);
            Borrow savedBorrow = borrowService.saveBorrow(borrow);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrow);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
