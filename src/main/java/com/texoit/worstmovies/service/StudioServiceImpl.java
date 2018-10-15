package com.texoit.worstmovies.service;

import com.texoit.worstmovies.dto.StudioWinCountDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.repository.StudioRepository;
import com.texoit.worstmovies.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudioServiceImpl implements StudioService {

    @Autowired
    StudioRepository studioRepository;

    @Override
    public Collection<StudioWinCountDTO> findAll() throws EmptySearchException {
        return Validator.getNonEmptyCollection(studioRepository.findStudiosWinCount());
    }
}
