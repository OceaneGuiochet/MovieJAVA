package com.ndroc.rocmovies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;
import com.ndroc.rocmovies.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void testGetAllMovies() {
        Style actionStyle = new Style();
        actionStyle.setStyleId(1);
        actionStyle.setStyleName("ACTION");

        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setTitle("Test Movie");
        movie.setStyle(actionStyle);

        when(movieRepository.findAll()).thenReturn(List.of(movie));

        List<Movie> movies = movieService.getAllMovies();
        assertEquals(1, movies.size());
        assertEquals("Test Movie", movies.get(0).getTitle());
        assertEquals("ACTION", movies.get(0).getStyle().getStyleName());
    }

    @Test
    public void testGetMovieById() {
        Movie movie = new Movie();
        movie.setMovieId(2);
        movie.setTitle("Another Movie");

        when(movieRepository.findById(2)).thenReturn(Optional.of(movie));

        Optional<Movie> result = movieService.getMovieById(2);
        assertEquals(true, result.isPresent());
        assertEquals("Another Movie", result.get().getTitle());
    }

    @Test
    public void testGetMoviesByStyleId() {
        Style sfStyle = new Style();
        sfStyle.setStyleId(2);
        sfStyle.setStyleName("SF");

        Movie movie1 = new Movie();
        movie1.setMovieId(3);
        movie1.setTitle("SF Movie 1");
        movie1.setStyle(sfStyle);

        Movie movie2 = new Movie();
        movie2.setMovieId(4);
        movie2.setTitle("SF Movie 2");
        movie2.setStyle(sfStyle);

        when(movieRepository.findByStyleStyleId(2)).thenReturn(List.of(movie1, movie2));

        List<Movie> movies = movieService.getMoviesByStyle(2);
        assertEquals(2, movies.size());
        assertEquals("SF Movie 1", movies.get(0).getTitle());
        assertEquals("SF Movie 2", movies.get(1).getTitle());
    }

    @Test
    public void testGetMoviesPaginated() {
        Movie movie = new Movie();
        movie.setMovieId(5);
        movie.setTitle("Paged Movie");

        Page<Movie> page = new PageImpl<>(List.of(movie));
        when(movieRepository.findAll(PageRequest.of(0, 1))).thenReturn(page);

        Page<Movie> result = movieService.getMoviesPaginated(PageRequest.of(0, 1));
        assertEquals(1, result.getContent().size());
        assertEquals("Paged Movie", result.getContent().get(0).getTitle());
    }
}
