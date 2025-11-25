package com.ndroc.rocmovies.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    

    List<Movie> findByStyle(Style style);
    Page<Movie> findAll(Pageable pageable);
    List<Movie> findByStyleStyleId(Integer styleId);

}
