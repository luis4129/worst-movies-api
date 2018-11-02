package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.dto.YearWinCountDTO;
import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.exception.WinnerDeleteException;
import com.texoit.worstmovies.model.Movie;
import com.texoit.worstmovies.service.MovieService;
import com.texoit.worstmovies.util.CollectionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    MovieService movieService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Movie> findAll() throws EmptySearchException {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findById(@PathVariable("id") Long id) throws EmptySearchException {
        return movieService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable("id") Long id) throws WinnerDeleteException, EmptySearchException {
        movieService.delete(id);
    }

    @GetMapping("/years/{year}/winners")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Movie> findWinners(@PathVariable("year") Integer year) throws EmptySearchException {
        return movieService.findWinnersByYear(year);
    }

    @GetMapping("/years/multipleWinners")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Collection<YearWinCountDTO>> findWinnerCountByYear() throws EmptySearchException {
        return CollectionWrapper.wrap("years", movieService.findYearsWithMultipleWinners());
    }

}
