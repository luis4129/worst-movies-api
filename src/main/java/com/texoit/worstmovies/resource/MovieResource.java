package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.model.Movie;
import com.texoit.worstmovies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    MovieService movieService;

    @GetMapping("/winners")
    public ResponseEntity<Collection<Movie>> findWinners() {
        Collection<Movie> movies = movieService.findWinners();

        if (movies.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<Movie>> findAll() {
        Collection<Movie> movies = movieService.findAll();

        if (movies.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
        Optional<Movie> movie = movieService.findById(id);

        if (!movie.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(movie.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> delete(@PathVariable("id") Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
