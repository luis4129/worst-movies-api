package com.texoit.worstmovies.repository;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.model.Producer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProducerRepository extends CrudRepository<Producer, Long> {

    @Query(nativeQuery = true)
    Collection<ProducerWinIntervalDTO> findProducersOrderByHighestIntervals();

    @Query(nativeQuery = true)
    Collection<ProducerWinIntervalDTO> findProducersOrderByLowestIntervals();

}
