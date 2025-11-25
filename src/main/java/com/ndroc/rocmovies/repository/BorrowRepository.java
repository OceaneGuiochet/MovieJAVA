package com.ndroc.rocmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ndroc.rocmovies.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
