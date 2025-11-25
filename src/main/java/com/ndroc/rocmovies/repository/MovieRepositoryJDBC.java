package com.ndroc.rocmovies.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.ndroc.rocmovies.entity.Movie;
import com.ndroc.rocmovies.entity.Style;

@Repository
public class MovieRepositoryJDBC {

    private final DataSource dataSource;

    public MovieRepositoryJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT m.movieId, m.title, m.productionYear, s.styleId, s.styleName " +
                "FROM movie m " +
                "JOIN style s ON m.styleId = s.styleId";

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Style style = new Style();
                style.setStyleId(rs.getInt("styleId"));
                style.setStyleName(rs.getString("styleName"));

                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("movieId"));
                movie.setTitle(rs.getString("title"));
                movie.setProductionYear(rs.getInt("productionYear"));
                movie.setStyle(style);

                movies.add(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    public void save(Movie movie) {
        String sql = "INSERT INTO movie (title, productionYear, styleId) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getTitle());
            stmt.setInt(2, movie.getProductionYear());
            stmt.setInt(3, movie.getStyle().getStyleId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
