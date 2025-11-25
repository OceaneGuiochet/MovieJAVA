package com.ndroc.rocmovies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.repository.MovieRepositoryJDBC;

@Service(value = "MovieService1")
@Primary
public class MovieService1 implements IMovieService {

    private final MovieRepositoryJDBC movieRepository;

    @Autowired
    public MovieService1(MovieRepositoryJDBC movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getListMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(long id) {
        return movieRepository.findAll().stream()
                .filter(m -> m.getMovieId() == id)
                .findFirst();
    }

    @Override
    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> getListMoviesByStyle(Style style) {
        return movieRepository.findAll().stream()
                .filter(m -> m.getStyle() == style)
                .toList();
    }

    @Override
    public List<Movie> getMoviesBetween(int startYear, int endYear) {
        return movieRepository.findAll().stream()
                .filter(m -> m.getProductionYear() >= startYear && m.getProductionYear() <= endYear)
                .toList();
    }
}
