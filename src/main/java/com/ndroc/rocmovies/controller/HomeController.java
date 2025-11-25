package com.ndroc.rocmovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.service.MovieService;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String displayHomePage(@RequestParam(name = "style", required = false) Integer styleId, Model model) {
        List<Movie> movies;
        if (styleId != null) {
            movies = movieService.getMoviesByStyle(styleId);
        } else {
            movies = movieService.getAllMovies();
        }

        List<Style> styles = movieService.getAllStyles();

        model.addAttribute("movies", movies);
        model.addAttribute("styles", styles);
        model.addAttribute("selectedStyle", styleId);

        return "home";
    }

    @GetMapping("/film/{id}")
    public String displayMovieDetail(@PathVariable Integer id, Model model) {
        Movie movie = movieService.getMovieById(id).orElse(null);
        if (movie == null) {
            return "redirect:/home";
        }
        model.addAttribute("movie", movie);
        return "movie-detail";
    }
}
