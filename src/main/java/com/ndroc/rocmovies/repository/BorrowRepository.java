package com.ndroc.rocmovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndroc.rocmovies.entity.Borrow;
import com.ndroc.rocmovies.entity.Style;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    // 3. Films empruntés par un client
    List<Borrow> findByCustomerCustomerId(Integer customerId);

    // 4. Clients ayant emprunté au moins un film d’un genre
    List<Borrow> findDistinctByMovieStyle(Style style);
}
