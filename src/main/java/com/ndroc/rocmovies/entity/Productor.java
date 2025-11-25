package com.ndroc.rocmovies.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "productor")
public class Productor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productorId;

    private String name;

    @OneToMany(mappedBy = "productor")
    private List<Movie> movies;

    public Integer getProductorId() {
        return productorId;
    }

    public void setProductorId(Integer productorId) {
        this.productorId = productorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
