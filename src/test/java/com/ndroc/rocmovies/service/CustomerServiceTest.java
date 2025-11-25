package com.ndroc.rocmovies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ndroc.rocmovies.entity.Customer;
import com.ndroc.rocmovies.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers() {
        Customer c1 = new Customer();
        c1.setCustomerId(1);
        c1.setName("Alice");

        Customer c2 = new Customer();
        c2.setCustomerId(2);
        c2.setName("Bob");

        when(customerRepository.findAll()).thenReturn(List.of(c1, c2));

        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(2, customers.size());
        assertEquals("Alice", customers.get(0).getName());
        assertEquals("Bob", customers.get(1).getName());
    }

    @Test
    public void testGetCustomerByIdFound() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Alice");

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1);
        assertEquals("Alice", result.getName());
        assertEquals(1, result.getCustomerId());
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        when(customerRepository.findById(999)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            customerService.getCustomerById(999);
        });
        assertEquals("Customer not found", exception.getMessage());
    }
}
