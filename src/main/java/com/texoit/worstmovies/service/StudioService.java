package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.StudioWinCountDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface StudioService {

    Collection<StudioWinCountDTO> findAll() throws EmptySearchException;

}
