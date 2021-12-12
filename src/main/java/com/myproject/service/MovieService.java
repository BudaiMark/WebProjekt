package com.myproject.service;


import com.myproject.model.Movie;

import com.myproject.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired private MovieRepository repo;

    public List<Movie> listAll(){
        return (List<Movie>) repo.findAll();
    }

    public void save(Movie movie) {
        repo.save(movie);
    }

    public Movie get(Integer id) throws MovieException {
        Optional<Movie> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new MovieException("Could not find movie in the database with ID" + id);
    }

    public void delete(Integer id) throws MovieException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new MovieException("Could not find movie in the database with ID" + id);
        }
        repo.deleteById(id);
    }
}
