package com.ndroc.rocmovies.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.service.MovieService;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    public void testGetMovieByIdFound() throws Exception {
        Style actionStyle = new Style();
        actionStyle.setStyleId(1);
        actionStyle.setStyleName("ACTION");

        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setTitle("A");
        movie.setStyle(actionStyle);

        when(movieService.getMovieById(1)).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/movie/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("A"))
               .andExpect(jsonPath("$.style.styleName").value("ACTION"));
    }

    @Test
    public void testGetMovieByIdNotFound() throws Exception {
        when(movieService.getMovieById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/movie/999"))
               .andExpect(status().isInternalServerError()); 
    }

    @Test
    public void testPostValidMovie() throws Exception {
        String json = """
        {
            "movieId": 6,
            "title": "New Movie",
            "style": { "styleId": 1 },
            "productionYear": 2025
        }
        """;

        mockMvc.perform(post("/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
               .andExpect(status().isOk()); // ton controller ne renvoie pas 201
    }

    @Test
    public void testGetAllMovies() throws Exception {
        Style actionStyle = new Style();
        actionStyle.setStyleId(1);
        actionStyle.setStyleName("ACTION");

        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setTitle("Movie1");
        movie.setStyle(actionStyle);

        when(movieService.getAllMovies()).thenReturn(java.util.List.of(movie));

        mockMvc.perform(get("/movie"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].title").value("Movie1"))
               .andExpect(jsonPath("$[0].style.styleName").value("ACTION"));
    }
}
