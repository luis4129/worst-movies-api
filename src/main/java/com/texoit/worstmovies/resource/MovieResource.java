package com.texoit.worstmovies.resource;

import com.texoit.worstmovies.model.Movie;
import com.texoit.worstmovies.model.Producer;
import com.texoit.worstmovies.model.Studio;
import com.texoit.worstmovies.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    ImportService importService;

    @PostMapping("/import")
    public Collection<Movie> importData(String fileName) throws IOException {
        return importService.importData(Movie.class, fileName);
    }

}
