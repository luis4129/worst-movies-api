package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.dto.StudioWinCountDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface ProducerService {

    ProducerWinIntervalDTO findHighestWinInterval();
    ProducerWinIntervalDTO findLowestWinInterval();
}
