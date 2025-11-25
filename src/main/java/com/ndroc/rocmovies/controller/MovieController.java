package com.ndroc.rocmovies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.service.MovieService;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam("style") Optional<Style> style) {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("movie/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("movie")
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(value = "style", required = false) Integer styleId) {
        List<Movie> movies = (styleId != null)
                ? movieService.getMoviesByStyle(styleId)
                : movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("movie/page")
    public ResponseEntity<List<Movie>> getMoviesPaginated(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.getMoviesPaginated(pageable).getContent());
    }

    @PostMapping("movie")
    public ResponseEntity<String> addMovie(@RequestBody @Validated Movie movie) {
        movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie created successfully");
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        return movieService.getMovieById(id)
                .map(m -> {
                    movieService.deleteMovie(id);
                    return ResponseEntity.ok("Movie deleted successfully");
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found"));
    }
}
