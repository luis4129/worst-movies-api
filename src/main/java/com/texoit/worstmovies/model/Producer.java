package com.texoit.worstmovies.model;

import java.util.ArrayList;
import java.util.Collection;

public class Producer {

    private Long id;
    private String name;
    private Collection<Movie> movies;

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
        if (movies == null)
            movies = new ArrayList<Movie>();
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }
}
