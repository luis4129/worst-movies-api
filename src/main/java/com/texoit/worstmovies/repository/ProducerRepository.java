package com.texoit.worstmovies.repository;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.model.Producer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    @Query("SELECT new com.texoit.worstmovies.dto.ProducerWinIntervalDTO(p.name, MAX(m.year) - MIN(m.year), MIN(m.year), MAX(m.year)) FROM Producer p INNER JOIN p.movies m WHERE m.winner = true GROUP BY p.name ORDER BY 2 DESC")
    Collection<ProducerWinIntervalDTO> findProducersHighestInterval();

    @Query("SELECT new com.texoit.worstmovies.dto.ProducerWinIntervalDTO(p.name, m1.year - m2.year, m1.year, m2.year) FROM Producer p INNER JOIN p.movies m1 INNER JOIN p.movies m2 WHERE m1.winner = true AND m2.winner = true AND m1.year > m2.year ORDER BY 2")
    Collection<ProducerWinIntervalDTO> findProducersLowestInterval();

}
