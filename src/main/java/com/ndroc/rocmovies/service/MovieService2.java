package com.ndroc.rocmovies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;

@Service(value = "MovieService2")
public class MovieService2 implements IMovieService {

    /**
     * Fournit une liste de films 'en dur'
     * en attendant de pouvoir utiliser une base de données
     * 
     * @return
     */
    private static List<Movie> getDefaultList() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        movies.add(new Movie());
        movies.add(new Movie());
        movies.add(new Movie());
        movies.add(new Movie());

        return movies;
    }

    private List<Movie> movieList;

    /**
     * Liste complète de tous les films
     */
    @Override
    public List<Movie> getListMovies() {
        if (movieList == null) {
            movieList = getDefaultList();
        }
        return movieList;
    }

    @Override
    public Optional<Movie> getMovieById(long id) {
        return getListMovies().stream().filter(m -> m.getMovieId() == id).findFirst();
    }

    public MovieService2() {
        System.out.println("Création du service MovieService");
    }

    @Override
    public void addMovie(Movie movie) {

        getListMovies().add(movie);
    }

    @Override
    public List<Movie> getListMoviesByStyle(Style style) {
        return getListMovies().stream().filter(m -> m.getStyle() == style).toList();

    }

    @Override
    public List<Movie> getMoviesBetween(int startYear, int endYear) {
        return getListMovies().stream()
                .filter(m -> m.getProductionYear() >= startYear && m.getProductionYear() <= endYear)
                .toList();
    }

}
