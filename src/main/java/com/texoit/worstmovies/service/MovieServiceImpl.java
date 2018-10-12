package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.YearWinCountDTO;
import com.texoit.worstmovies.model.Movie;
import com.texoit.worstmovies.dto.MovieImportDTO;
import com.texoit.worstmovies.model.Producer;
import com.texoit.worstmovies.model.Studio;
import com.texoit.worstmovies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    ImportService importService;

    @Autowired
    MovieRepository movieRepository;

    private static final String MOVIE_LIST_FILE = System.getProperties().get("user.dir") + "\\csv-data\\movielist.csv";

    @Override
    public void importData() throws IOException {
        Collection<Movie> movies = importService.importData(MovieImportDTO.class, MOVIE_LIST_FILE).stream().map(movieImportDTO -> new Movie(movieImportDTO)).collect(Collectors.toList());
        Map<String, Producer> producersByNames = getProducersMapByMovie(movies);
        Map<String, Studio> studiosByNames = getStudiosMapByMovie(movies);
        movies.forEach(movie -> {
            movie.setProducers(movie.getProducers().stream().map(producer -> producersByNames.get(producer.getName())).collect(Collectors.toList()));
            movie.setStudios(movie.getStudios().stream().map(studio-> studiosByNames.get(studio.getName())).collect(Collectors.toList()));
        });
        movieRepository.saveAll(movies);
    }

    @Override
    public Collection<Movie> findWinners() {
        return movieRepository.findMovieByWinner(true);
    }

    @Override
    public Collection<Movie> findAll() {
        return (Collection<Movie>) movieRepository.findAll();
    }

    @Override
    public Collection<YearWinCountDTO> findWinnerCountByYear() {
        return movieRepository.findWinnerCountByYear().stream().filter(yearWinCountDTO -> yearWinCountDTO.getWinnerCount() > 1).collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    private Map<String, Producer> getProducersMapByMovie(Collection<Movie> movies) {
        return movies.stream()
                .map(Movie::getProducers)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Producer::getName, producer -> producer, (producer1, producer2) -> {
                    producer1.getMovies().addAll(producer2.getMovies());
                    return producer1;
                }));
    }

    private Map<String, Studio> getStudiosMapByMovie(Collection<Movie> movies) {
        return movies.stream()
                .map(Movie::getStudios)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Studio::getName, studio -> studio, (studio1, studio2) -> {
                    studio1.getMovies().addAll(studio2.getMovies());
                    return studio1;
                }));
    }

    @Override
    public void delete(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if(!movie.isPresent())
            return;

        if(movie.get().isWinner())
            return;

        movieRepository.delete(movie.get());
    }
}
