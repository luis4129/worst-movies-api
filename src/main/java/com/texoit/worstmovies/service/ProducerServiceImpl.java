package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.repository.ProducerRepository;
import com.texoit.worstmovies.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    @Override
    public Collection<ProducerWinIntervalDTO> findHighestWinInterval() throws EmptySearchException {
        return Validator.getNonEmptyCollection(filterByHighestInterval(producerRepository.findProducersHighestInterval()));
    }

    @Override
    public Collection<ProducerWinIntervalDTO> findLowestWinInterval() throws EmptySearchException {
        return Validator.getNonEmptyCollection(filterByHighestInterval(producerRepository.findProducersLowestInterval()));
    }

    private Collection<ProducerWinIntervalDTO> filterByHighestInterval(Collection<ProducerWinIntervalDTO> intervals) {
        return intervals.stream().filter(interval -> interval.equals(intervals.stream().map(ProducerWinIntervalDTO::getInterval).findFirst().orElse(-1))).collect(Collectors.toList());
    }
}
