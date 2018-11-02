package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public interface ProducerService {

    Collection<ProducerWinIntervalDTO> findHighestWinInterval() throws EmptySearchException;
    Collection<ProducerWinIntervalDTO> findLowestWinInterval() throws EmptySearchException;
    Map<String, Collection<ProducerWinIntervalDTO>> findHighestAndLowestWinInterval() throws EmptySearchException;
}
