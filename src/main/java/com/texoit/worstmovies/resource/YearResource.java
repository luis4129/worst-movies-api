package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.dto.YearWinCountDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.service.MovieService;
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
@RequestMapping("/years")
public class YearResource {

    @Autowired
    MovieService movieService;

    @GetMapping("/multipleWinners")
    public ResponseEntity<Map<String, Collection<YearWinCountDTO>>> findWinnerCountByYear() {
        try {
            return new ResponseEntity(CollectionWrapper.wrap("years", movieService.findWinnerCountByYear()), HttpStatus.OK);
        } catch (EmptySearchException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
