package com.ndroc.rocmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ndroc.rocmovies.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
