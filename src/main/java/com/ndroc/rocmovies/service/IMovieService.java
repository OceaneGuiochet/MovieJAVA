package com.ndroc.rocmovies.service;

import java.util.List;
import java.util.Optional;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.MovieStyles;

public interface IMovieService {

    /** 
     * Liste complète de tous les films
     */
    List<Movie> getListMovies();

    Optional<Movie> getMovieById(long id);

    void addMovie(Movie movie);

    List<Movie> getListMoviesByStyle(MovieStyles style);
    List<Movie> getMoviesBetween(int startYear, int endYear);


}