package com.texoit.worstmovies.repository;

import com.texoit.worstmovies.dto.StudioWinCountDTO;
import com.texoit.worstmovies.model.Studio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StudioRepository extends CrudRepository<Studio, Long> {

    @Query("SELECT new com.texoit.worstmovies.dto.StudioWinCountDTO(s.name, count(m)) FROM Studio s INNER JOIN s.movies m WHERE m.winner = true GROUP BY s.name ORDER BY 2 DESC")
    Collection<StudioWinCountDTO> findStudiosWinCount();

}
