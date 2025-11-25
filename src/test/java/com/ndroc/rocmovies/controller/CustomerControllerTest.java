package com.ndroc.rocmovies.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ndroc.rocmovies.entity.Borrow;
import com.ndroc.rocmovies.entity.Customer;
import com.ndroc.rocmovies.service.BorrowService;
import com.ndroc.rocmovies.service.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private BorrowService borrowService;

    @Test
    public void testGetAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Alice");

        when(customerService.getAllCustomers()).thenReturn(List.of(customer));

        mockMvc.perform(get("/customers"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].customerId").value(1))
               .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    public void testGetCustomerByIdFound() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Alice");

        when(customerService.getAllCustomers()).thenReturn(List.of(customer));

        mockMvc.perform(get("/customers/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.customerId").value(1))
               .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(List.of());

        mockMvc.perform(get("/customers/999"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testGetBorrowsByCustomerFound() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Alice");
        Borrow borrow = new Borrow();
        borrow.setBorrowId(1);
        borrow.setStatus("EN_COURS");
        customer.setBorrows(List.of(borrow));

        when(customerService.getAllCustomers()).thenReturn(List.of(customer));

        mockMvc.perform(get("/customers/1/borrows"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].borrowId").value(1))
               .andExpect(jsonPath("$[0].status").value("EN_COURS"));
    }

    @Test
    public void testGetBorrowsByCustomerNotFound() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(List.of());

        mockMvc.perform(get("/customers/999/borrows"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testAddBorrowForCustomerFound() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("Alice");

        Borrow borrow = new Borrow();
        borrow.setBorrowId(1);
        borrow.setStatus("EN_COURS");

        when(customerService.getAllCustomers()).thenReturn(List.of(customer));
        when(borrowService.saveBorrow(borrow)).thenReturn(borrow);

        String json = """
        {
            "borrowId":1,
            "status":"EN_COURS"
        }
        """;

        mockMvc.perform(post("/customers/1/borrows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.borrowId").value(1))
               .andExpect(jsonPath("$.status").value("EN_COURS"));
    }

    @Test
    public void testAddBorrowForCustomerNotFound() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(List.of());

        String json = """
        {
            "borrowId":1,
            "status":"EN_COURS"
        }
        """;

        mockMvc.perform(post("/customers/999/borrows")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
               .andExpect(status().isNotFound());
    }
}
