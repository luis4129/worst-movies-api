package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;
import com.texoit.worstmovies.dto.StudioWinCountDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.model.Producer;
import com.texoit.worstmovies.service.ProducerService;
import com.texoit.worstmovies.service.StudioService;
import com.texoit.worstmovies.util.CollectionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/producers")
public class ProducerResource {

    @Autowired
    ProducerService producerService;

    @GetMapping("/interval")
    public ResponseEntity<Map<String, Collection<ProducerWinIntervalDTO>>> studios() {
        try {
            Map<String, Collection<ProducerWinIntervalDTO>> intervalsMap = new HashMap<>();
            intervalsMap.put("min", producerService.findHighestWinInterval());
            intervalsMap.put("max", producerService.findLowestWinInterval());
            return new ResponseEntity(intervalsMap, HttpStatus.OK);
        } catch (EmptySearchException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

