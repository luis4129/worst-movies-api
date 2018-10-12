package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.dto.StudioWinCountDTO;
import com.texoit.worstmovies.model.Producer;
import com.texoit.worstmovies.repository.ProducerRepository;
import com.texoit.worstmovies.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    @Override
    public ProducerWinIntervalDTO findHighestWinInterval() {
        return producerRepository.findHighestProducersInterval().stream().findFirst().get();
    }

    @Override
    public ProducerWinIntervalDTO findLowestWinInterval() {
        return producerRepository.findLowestProducersInterval().stream().findFirst().get();
    }
}
