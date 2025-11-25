package com.ndroc.rocmovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.service.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("movie/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @GetMapping("movie")
    public List<Movie> getAllMovies(@RequestParam(value = "style", required = false) Integer styleId) {
        if (styleId != null) {
            return movieService.getMoviesByStyle(styleId);
        }
        return movieService.getAllMovies();
    }

    @GetMapping("movie/page")
    public List<Movie> getMoviesPaginated(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieService.getMoviesPaginated(pageable).getContent();
    }

    @PostMapping("movie")
    public void addMovie(@RequestBody @Validated Movie movie){
        movieService.addMovie(movie);
    }
}
