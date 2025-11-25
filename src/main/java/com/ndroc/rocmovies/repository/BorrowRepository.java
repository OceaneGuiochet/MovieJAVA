package com.ndroc.rocmovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndroc.rocmovies.entity.Borrow;
import com.ndroc.rocmovies.entity.Style;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    List<Borrow> findByCustomerCustomerId(Integer customerId);

    List<Borrow> findDistinctByMovieStyle(Style style);
}
