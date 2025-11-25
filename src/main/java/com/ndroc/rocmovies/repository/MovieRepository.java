package com.ndroc.rocmovies.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;

@Component
public class MovieRepository {

    private final List<Movie> movies;

    public MovieRepository() {
        movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());
        movies.add(new Movie());
        movies.add(new Movie());
        movies.add(new Movie());
    }

    public List<Movie> findAll() {
        return new ArrayList<>(movies);
    }

    public void save(Movie movie) {
        movies.add(movie);
    }
}
