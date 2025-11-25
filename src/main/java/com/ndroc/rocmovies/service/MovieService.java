package com.ndroc.rocmovies.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.repository.MovieRepository;
import com.ndroc.rocmovies.repository.MovieRepositoryJDBC;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Integer id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getMoviesByStyle(Style style) {
        return movieRepository.findByStyle(style);
    }

    public Page<Movie> getMoviesPaginated(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public List<Movie> getMoviesByStyle(Integer styleId) {
        return movieRepository.findByStyleStyleId(styleId);
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteMovie(Integer id) {
    movieRepository.deleteById(id);
}


}
