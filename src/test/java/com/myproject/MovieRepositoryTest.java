package com.myproject;


import com.myproject.model.Movie;
import com.myproject.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class MovieRepositoryTest {
    @Autowired private MovieRepository repo;

    @Test
    public void testAddNew(){
        Movie movie = new Movie();
        movie.setTitle("Title");
        movie.setDirector("A director");
        movie.setGenre("Some genre");
        movie.setReleaseYear(1234);

        Movie savedMovie = repo.save(movie);

        Assertions.assertThat(savedMovie).isNotNull();
        Assertions.assertThat(savedMovie.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Movie> movies = repo.findAll();
        Assertions.assertThat(movies).hasSizeGreaterThan(0);

        for(Movie movie : movies){
            System.out.println(movie);
        }
    }

    @Test
    public void testUpdate(){
        Integer movieId = 1;
        Optional<Movie> optionalMovie = repo.findById(movieId);
        Movie movie = optionalMovie.get();
        movie.setReleaseYear(4321);
        repo.save(movie);

        Movie updateMovie = repo.findById(movieId).get();
        Assertions.assertThat(updateMovie.getReleaseYear()).isEqualTo(4321);
    }

    @Test
    public void testGet(){
        Integer movieId = 1;
        Optional<Movie> optionalMovie = repo.findById(movieId);
        Assertions.assertThat(optionalMovie).isPresent();
        System.out.println(optionalMovie.get() );
    }

    @Test
    public void testDelete(){
        Integer movieId = 1;
        repo.deleteById(movieId);

        Optional<Movie> optionalMovie = repo.findById(movieId);
        Assertions.assertThat(optionalMovie).isNotPresent();
    }
}
