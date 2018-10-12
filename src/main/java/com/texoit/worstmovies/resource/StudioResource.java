package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.dto.StudioWinCountDTO;
import com.texoit.worstmovies.service.StudioService;
import com.texoit.worstmovies.util.CollectionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/studios")
public class StudioResource {

    @Autowired
    StudioService studioService;

    @GetMapping
    public ResponseEntity<Map<String, Collection<StudioWinCountDTO>>> studios() {
        Collection studios = studioService.findAll();

        if (studios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(CollectionWrapper.wrap("studios", studios), HttpStatus.OK);
    }

}
