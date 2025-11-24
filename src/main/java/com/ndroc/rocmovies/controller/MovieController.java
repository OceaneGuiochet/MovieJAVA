package com.ndroc.rocmovies.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.MovieStyles;
import com.ndroc.rocmovies.service.IMovieService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class MovieController {

    //injection de dépendance
    @Autowired
    @Qualifier("MovieService2")
    private IMovieService service;
    
    //routes
    @GetMapping("movie/{id}")
    public Movie getMovieById(@PathVariable long id){
        Optional<Movie> opt = service.getMovieById(id);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "movie not found");
        }
    }

    @GetMapping("movie")
    public List<Movie> getAllMovies(@RequestParam("style") Optional<MovieStyles> style) {
        if(style.isPresent()){
            return service.getListMoviesByStyle(style.get());
        }
        return service.getListMovies();
    }
    
    @PostMapping("movie")
    public void addMovie(@RequestBody Movie movie){
        service.addMovie(movie);
    }
    
    
}
