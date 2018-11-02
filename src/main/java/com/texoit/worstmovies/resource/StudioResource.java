package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.dto.StudioWinCountDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.service.StudioService;
import com.texoit.worstmovies.util.CollectionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/studios")
public class StudioResource {

    @Autowired
    StudioService studioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Collection<StudioWinCountDTO>> studios() throws EmptySearchException {
        return CollectionWrapper.wrap("studios", studioService.findAll());
    }

}
