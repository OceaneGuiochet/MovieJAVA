package com.ndroc.rocmovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getAllCustomers()
                .stream()
                .filter(c -> c.getCustomerId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @GetMapping("/customers/{id}/borrows")
    public List<Borrow> getBorrowsByCustomer(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return customer.getBorrows();
    }

    @PostMapping("customers/{id}/borrows")
    public Borrow addBorrowForCustomer(@PathVariable Integer id, @RequestBody Borrow borrow) {
        Customer customer = customerService.getCustomerById(id);
        borrow.setCustomer(customer);
        return borrowService.saveBorrow(borrow);
    }
}
