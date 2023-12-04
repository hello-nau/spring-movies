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
    public User findByName(String userName) {
        return userRepository.findByName(userName);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Set<Movie> getMovies(String userName) {
        return userRepository.getMovies(userName);
    }
    public String addMovie(String userName, Movie movie)  {
        return userRepository.addMovie(userName, movie);
    }
    public String delete(String userName) {
        return userRepository.delete(userName);
    }

}
