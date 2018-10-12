package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.StudioWinCountDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface StudioService {

    Collection<StudioWinCountDTO> findAll();

}
