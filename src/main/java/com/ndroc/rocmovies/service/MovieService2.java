package com.ndroc.rocmovies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.MovieStyles;

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
        movies.add(new Movie(1, "Cloud Atlas", MovieStyles.SF, 2012));
        movies.add(new Movie(2, "Shutter Island", MovieStyles.THRILLER, 2010));
        movies.add(new Movie(3, "Interstellar", MovieStyles.SF, 2018));
        movies.add(new Movie(4, "Pulp Fiction", MovieStyles.ACTION, 2001));
        movies.add(new Movie(5, "Mulholland Drive", MovieStyles.THRILLER, 2001));

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
        return getListMovies().stream().filter(m -> m.getIdMovie() == id).findFirst();
    }

    public MovieService2() {
        System.out.println("Création du service MovieService");
    }

    @Override
    public void addMovie(Movie movie) {

        getListMovies().add(movie);
    }

    @Override
    public List<Movie> getListMoviesByStyle(MovieStyles style) {
        return getListMovies().stream().filter(m -> m.getStyle() == style).toList();

    }

    @Override
    public List<Movie> getMoviesBetween(int startYear, int endYear) {
        return getListMovies().stream()
                .filter(m -> m.getProductionYear() >= startYear && m.getProductionYear() <= endYear)
                .toList();
    }

}
