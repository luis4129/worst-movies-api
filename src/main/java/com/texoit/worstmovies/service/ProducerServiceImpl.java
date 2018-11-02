package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.repository.ProducerRepository;
import com.texoit.worstmovies.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    @Override
    public Collection<ProducerWinIntervalDTO> findHighestWinInterval() throws EmptySearchException {
        return Validator.getNonEmptyCollection(filterByTopIntervals(producerRepository.findProducersOrderByHighestIntervals()));
    }

    @Override
    public Collection<ProducerWinIntervalDTO> findLowestWinInterval() throws EmptySearchException {
        return Validator.getNonEmptyCollection(filterByTopIntervals(producerRepository.findProducersOrderByLowestIntervals()));
    }

    private Collection<ProducerWinIntervalDTO> filterByTopIntervals(Collection<ProducerWinIntervalDTO> intervals) {
        return intervals.stream().filter(interval -> interval.getInterval().compareTo(intervals.stream().map(ProducerWinIntervalDTO::getInterval).findFirst().orElse(-1)) == 0).collect(Collectors.toList());
    }

    @Override
    public Map<String, Collection<ProducerWinIntervalDTO>> findHighestAndLowestWinInterval() throws EmptySearchException {
        Map<String, Collection<ProducerWinIntervalDTO>> intervalsMap = new HashMap<>();
        intervalsMap.put("min", this.findHighestWinInterval());
        intervalsMap.put("max", this.findLowestWinInterval());
        return intervalsMap;
    }
}
