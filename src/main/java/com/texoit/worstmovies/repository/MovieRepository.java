package com.texoit.worstmovies.repository;

import com.texoit.worstmovies.dto.YearWinCountDTO;
import com.texoit.worstmovies.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Query("SELECT new com.texoit.worstmovies.dto.YearWinCountDTO(m.year, count(m)) FROM Movie m WHERE m.winner = true GROUP BY m.year ORDER BY 2 DESC")
    Collection<YearWinCountDTO> findWinnerCountByYear();

    Collection<Movie> findMovieByWinnerAndYear(boolean winner, Integer year);

}
