package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.exception.EmptySearchException;
import com.texoit.worstmovies.exception.WinnerDeleteException;
import com.texoit.worstmovies.model.Movie;
import com.texoit.worstmovies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    MovieService movieService;

    @GetMapping("/winners/{year}")
    public ResponseEntity<Collection<Movie>> findWinners(@PathVariable("year") Integer year) {
        try {
            return new ResponseEntity<>(movieService.findWinnersByYear(year), HttpStatus.OK);
        } catch (EmptySearchException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Movie>> findAll() {
        try {
            return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
        } catch (EmptySearchException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(movieService.findById(id), HttpStatus.OK);
        } catch (EmptySearchException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            movieService.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (WinnerDeleteException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (EmptySearchException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
