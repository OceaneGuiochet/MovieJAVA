package com.ndroc.rocmovies.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.MovieStyles;

@Component
public class MovieRepository {

    private final List<Movie> movies;

    public MovieRepository() {
        movies = new ArrayList<>();
        movies.add(new Movie(1, "Cloud Atlas", MovieStyles.SF, 2012));
        movies.add(new Movie(2, "Shutter Island", MovieStyles.THRILLER, 2010));
        movies.add(new Movie(3, "Interstellar", MovieStyles.SF, 2018));
        movies.add(new Movie(4, "Pulp Fiction", MovieStyles.ACTION, 2001));
        movies.add(new Movie(5, "Mulholland Drive", MovieStyles.THRILLER, 2001));
    }

    public List<Movie> findAll() {
        return new ArrayList<>(movies);
    }

    public void save(Movie movie) {
        movies.add(movie);
    }
}
