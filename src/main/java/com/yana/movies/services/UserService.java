package com.yana.movies.services;

import com.yana.movies.entities.User;
import com.yana.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yana.movies.entities.Movie;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User save (User user) {
        return userRepository.save(user);
    }
    public User findByName(java.lang.String userName) {
        return userRepository.findByName(userName);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public java.lang.String update(java.lang.String userName, Movie movie) {
        return userRepository.update(userName, movie);
    }
    public Set<Movie> getMovies(java.lang.String userName) {
        return userRepository.getMovies(userName);
    }
    public java.lang.String addMovie(java.lang.String userName, Movie movie)  {
        return userRepository.addMovie(userName, movie);
    }
    public java.lang.String deleteMovie (java.lang.String userName, Movie movieToDelete) {
        return userRepository.deleteMovie(userName, movieToDelete);
    }
    public java.lang.String delete(java.lang.String userName) {
        return userRepository.delete(userName);
    }

}
