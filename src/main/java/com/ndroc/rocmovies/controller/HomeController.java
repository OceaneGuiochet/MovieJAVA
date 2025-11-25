package com.ndroc.rocmovies.controller;

import java.util.Arrays;
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
import com.ndroc.rocmovies.service.MovieService1;

@Controller
public class HomeController {

    @Autowired
    private MovieService1 movieService1;

    @RequestMapping(value = { "", "/", "home" })
    public String displayHomePage(@RequestParam(name = "style", required = false) Style style, Model model) {
        List<Movie> movies;

        if (style != null) {
            movies = movieService1.getListMoviesByStyle(style);
        } else {
            movies = movieService1.getListMovies();
        }

        // List<Style> styles = Arrays.asList(Style.values());

        // model.addAttribute("movies", movies);
        // model.addAttribute("styles", styles);
        // model.addAttribute("selectedStyle", style);

        return "home";
    }

    @GetMapping("/film/{id}")
    public String displayMovieDetail(@PathVariable long id, Model model) {
        Movie movie = movieService1.getMovieById(id).orElse(null);
        if (movie == null) {
            return "redirect:/home";
        }
        model.addAttribute("movie", movie);
        return "movie-detail";
    }

}
