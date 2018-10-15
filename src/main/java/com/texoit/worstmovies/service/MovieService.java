package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.YearWinCountDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.exception.WinnerDeleteException;
import com.texoit.worstmovies.model.Movie;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public interface MovieService {

    void importData() throws IOException;
    Collection<Movie> findWinners() throws EmptySearchException;
    Collection<Movie> findAll() throws EmptySearchException;
    Movie findById(Long id) throws EmptySearchException;
    Collection<YearWinCountDTO> findWinnerCountByYear() throws EmptySearchException;
    void delete(Long id) throws WinnerDeleteException, EmptySearchException;
}
