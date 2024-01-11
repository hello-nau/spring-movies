package com.yana.movies.services;

import com.yana.movies.entities.Movie;
import com.yana.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }
    public Movie findById(String stringName) {
       //TODO add check if it is a valid name
        return movieRepository.findByName(stringName);
    }
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
    public String update(String stringName, Movie movie) {
        return movieRepository.update(stringName, movie);
    }
    public String delete(String stringName) {
        return movieRepository.delete(stringName);
    }

}

