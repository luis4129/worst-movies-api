package com.texoit.worstmovies.model;

import java.util.ArrayList;
import java.util.Collection;

public class Movie {

    private Long id;
    private Integer year;
    private String title;
    private Collection<Studio> studios;
    private Collection<Producer> producers;
    private boolean winner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Studio> getStudios() {
        if (studios == null)
            studios = new ArrayList<Studio>();
        return studios;
    }

    public void setStudios(Collection<Studio> studios) {
        this.studios = studios;
    }

    public Collection<Producer> getProducers() {
        if (producers == null)
            producers = new ArrayList<Producer>();
        return producers;
    }

    public void setProducers(Collection<Producer> producers) {
        this.producers = producers;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
