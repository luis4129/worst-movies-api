package com.texoit.worstmovies.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Entity
public class Studio {

    @Id
    @GeneratedValue
    private Long id;

    @JsonValue
    private String name;

    @ManyToMany(mappedBy = "studios")
    @JsonBackReference
    private Collection<Movie> movies;

    public Studio() {
        super();
    }

    public Studio(String name, Movie movie) {
        this.name = name;
        addMovie(movie);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Movie> getMovies() {
        if (this.movies == null)
            this.movies = new ArrayList<>();

        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        getMovies().add(movie);
    }
}
