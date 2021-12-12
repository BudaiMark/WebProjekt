package com.myproject.repository;


import com.myproject.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Long countById(Integer id);
}
