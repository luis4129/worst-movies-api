package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.YearWinCountDTO;
import com.texoit.worstmovies.model.Movie;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Component
public interface MovieService {

    void importData() throws IOException;
    Collection<Movie> findWinners();
    Collection<Movie> findAll();
    Optional<Movie> findById(Long id);
    Collection<YearWinCountDTO> findWinnerCountByYear();
    void delete(Long id);
}
