package com.texoit.worstmovies.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonValue;
import com.texoit.worstmovies.dto.ProducerWinIntervalDTO;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Producer.findProducersOrderByHighestIntervals",
                query = "SELECT P.NAME as PRODUCER, M1.YEAR - M2.YEAR AS INTERVAL, M2.YEAR AS PREVIOUS_WIN, M1.YEAR AS FOLLOWING_WIN FROM PRODUCER P INNER JOIN MOVIE_HAS_PRODUCERS MP ON P.ID = MP.PRODUCER_ID INNER JOIN MOVIE M1 ON M1.ID = MP.MOVIE_ID INNER JOIN MOVIE M2 ON M2.ID IN (SELECT IMP.MOVIE_ID FROM MOVIE_HAS_PRODUCERS IMP WHERE IMP.PRODUCER_ID = P.ID) WHERE M1.WINNER = 1 AND M2.WINNER = 1 AND M1.YEAR > M2.YEAR AND NOT EXISTS (SELECT 1 FROM MOVIE IM INNER JOIN MOVIE_HAS_PRODUCERS IMP ON IMP.MOVIE_ID = IM.ID WHERE IM.WINNER = 1 AND IMP.PRODUCER_ID = P.ID AND IM.YEAR > M2.YEAR AND IM.YEAR < M1.YEAR) ORDER BY 2 DESC",
                resultSetMapping = "producers_intervals"
        ),
        @NamedNativeQuery(
                name = "Producer.findProducersOrderByLowestIntervals",
                query = "SELECT P.NAME as PRODUCER, M1.YEAR - M2.YEAR AS INTERVAL, M2.YEAR AS PREVIOUS_WIN, M1.YEAR AS FOLLOWING_WIN FROM PRODUCER P INNER JOIN MOVIE_HAS_PRODUCERS MP ON P.ID = MP.PRODUCER_ID INNER JOIN MOVIE M1 ON M1.ID = MP.MOVIE_ID INNER JOIN MOVIE M2 ON M2.ID IN (SELECT IMP.MOVIE_ID FROM MOVIE_HAS_PRODUCERS IMP WHERE IMP.PRODUCER_ID = P.ID) WHERE M1.WINNER = 1 AND M2.WINNER = 1 AND M1.YEAR > M2.YEAR AND NOT EXISTS (SELECT 1 FROM MOVIE IM INNER JOIN MOVIE_HAS_PRODUCERS IMP ON IMP.MOVIE_ID = IM.ID WHERE IM.WINNER = 1 AND IMP.PRODUCER_ID = P.ID AND IM.YEAR > M2.YEAR AND IM.YEAR < M1.YEAR) ORDER BY 2",
                resultSetMapping = "producers_intervals"
        )
})
@SqlResultSetMapping(name = "producers_intervals",
    classes = {
        @ConstructorResult(targetClass=ProducerWinIntervalDTO.class, columns = {
            @ColumnResult(name = "PRODUCER", type = String.class),
            @ColumnResult(name = " INTERVAL", type = Integer.class),
            @ColumnResult(name = "PREVIOUS_WIN", type = Integer.class),
            @ColumnResult(name = "FOLLOWING_WIN", type = Integer.class)
        })
    }
)
public class Producer {

    @Id
    @GeneratedValue
    private Long id;

    @JsonValue
    private String name;

    @ManyToMany(mappedBy = "producers")
    @JsonBackReference
    private Collection<Movie> movies;

    public Producer() {
        super();
    }

    public Producer(String name, Movie movie) {
        this.name = name;
        addMovie(movie);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Movie> getMovies() {
        if (this.movies == null)
            this.movies = new ArrayList<>();

        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        getMovies().add(movie);
    }
}
