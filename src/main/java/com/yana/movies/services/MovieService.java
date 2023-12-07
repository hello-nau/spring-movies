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
    public Movie findById(java.lang.String stringName) {
        return movieRepository.findByName(stringName);
    }
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
    public java.lang.String update(java.lang.String stringName, Movie movie) {
        return movieRepository.update(stringName, movie);
    }
    public java.lang.String delete(java.lang.String stringName) {
        return movieRepository.delete(stringName);
    }

}

