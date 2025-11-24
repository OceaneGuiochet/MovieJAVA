package com.ndroc.rocmovies.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.MovieStyles;
import com.ndroc.rocmovies.repository.MovieRepository;


@ExtendWith(MockitoExtension.class)
public class MovieService1Test {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService1 movieService;

    @Test
    public void testGetListMovies() {
        when(movieRepository.findAll()).thenReturn(List.of(
            new Movie(1, "Test Movie", MovieStyles.ACTION, 2000)
        ));

        List<Movie> movies = movieService.getListMovies();
        assertEquals(1, movies.size());
        assertEquals("Test Movie", movies.get(0).getTitle());
    }

    @Test
    public void testGetMoviesBetween() {
        when(movieRepository.findAll()).thenReturn(List.of(
            new Movie(1, "A", MovieStyles.ACTION, 2000),
            new Movie(2, "B", MovieStyles.SF, 2010)
        ));

        List<Movie> movies = movieService.getMoviesBetween(2005, 2015);
        assertEquals(1, movies.size());
        assertEquals("B", movies.get(0).getTitle());
    }
}
