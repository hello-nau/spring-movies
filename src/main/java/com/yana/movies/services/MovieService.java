package com.yana.movies.services;

import com.amazonaws.services.dynamodbv2.xspec.M;
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
    public Movie findById(String movieName) {
        return movieRepository.findByName(movieName);
    }
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
    public String update(String movieName, Movie movie) {
        return movieRepository.update(movieName, movie);
    }
    public String delete(String movieName) {
        return movieRepository.delete(movieName);
    }

}

