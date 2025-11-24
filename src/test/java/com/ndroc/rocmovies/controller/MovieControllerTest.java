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
import com.ndroc.rocmovies.entity.MovieStyles;
import com.ndroc.rocmovies.service.MovieService1;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "MovieService2")
    private MovieService1 movieService;

    @Test
    public void testGetMovieByIdFound() throws Exception {
        Movie movie = new Movie(1, "A", MovieStyles.ACTION, 2000);
        when(movieService.getMovieById(1L)).thenReturn(Optional.of(movie));

        mockMvc.perform(get("/movie/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("A"));
    }

    @Test
    public void testGetMovieByIdNotFound() throws Exception {
        when(movieService.getMovieById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/movie/999"))
               .andExpect(status().isNotFound());
    }

    @Test
    public void testPostValidMovie() throws Exception {
        String json = """
        {"idMovie":6,"title":"New Movie","style":"ACTION","productionYear":2025}
        """;

        mockMvc.perform(post("/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
               .andExpect(status().isCreated());
    }

    @Test
    public void testPostInvalidMovie() throws Exception {
        String json = """
        {"idMovie":6,"title":"New Movie","style":"INVALID_STYLE","productionYear":2025}
        """;

        mockMvc.perform(post("/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
               .andExpect(status().isBadRequest());
    }
}
