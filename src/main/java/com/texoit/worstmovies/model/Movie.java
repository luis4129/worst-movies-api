package com.texoit.worstmovies.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.texoit.worstmovies.dto.MovieImportDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Entity
@JsonPropertyOrder({ "id", "year", "title", "studios", "producers", "winner" })
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "movie_has_studios", joinColumns = {@JoinColumn(name="movie_title")}, inverseJoinColumns = {@JoinColumn(name="studio_name")})
    @JsonManagedReference
    private Collection<Studio> studios;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "movie_has_producers", joinColumns = {@JoinColumn(name="movie_title")}, inverseJoinColumns = {@JoinColumn(name="producer_name")})
    @JsonManagedReference
    private Collection<Producer> producers;

    private String title;
    private Integer year;
    private boolean winner;

    public Movie() {
        super();
    }

    public Movie(MovieImportDTO movieImportDTO) {
        this.title = movieImportDTO.getTitle();
        this.year = movieImportDTO.getYear();
        this.winner = Optional.ofNullable(movieImportDTO.getWinner()).map(text -> text.matches("yes")).orElse(false);
        Arrays.asList(movieImportDTO.getProducers().split(", ")).stream().map(producerName -> Arrays.asList(producerName.split(" and "))).flatMap(Collection::stream).forEach(producerName -> addProducer(new Producer(producerName, this)));
        Arrays.asList(movieImportDTO.getStudios().split(", ")).stream().map(producerName -> Arrays.asList(producerName.split(" and "))).flatMap(Collection::stream).forEach(studioName -> addStudio(new Studio(studioName, this)));
    }

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
        if (this.studios == null)
            this.studios = new ArrayList<>();

        return studios;
    }

    public void setStudios(Collection<Studio> studios) {
        this.studios = studios;
    }

    public void addStudio(Studio studio) {
        getStudios().add(studio);
    }

    public Collection<Producer> getProducers() {
        if (this.producers == null)
            this.producers = new ArrayList<>();

        return producers;
    }

    public void setProducers(Collection<Producer> producers) {
        this.producers = producers;
    }

    public void addProducer(Producer producer) {
        getProducers().add(producer);
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
