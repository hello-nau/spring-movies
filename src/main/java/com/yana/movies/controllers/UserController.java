package com.yana.movies.controllers;

import com.yana.movies.entities.Movie;
import com.yana.movies.entities.User;

import java.util.logging.Logger;
import com.yana.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", produces = "application/json")
    public ResponseEntity<User> save (@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll () {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<User> findByName(@PathVariable(value = "userName") java.lang.String userName) {
        User user = userService.findByName(userName);
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/users/{userName}/movieList/{id}")
    public ResponseEntity<Movie> userMovie(@PathVariable(value = "userName") java.lang.String userName,
                                           @PathVariable(value = "id") java.lang.String stringId) {
        User user = userService.findByName(userName);
        Movie movie = user.getMovies().stream()
                .filter(m -> m.getId().equals(stringId))
                .findFirst()
                .orElse(null);
                    try {
                        // Define your response entity
                        java.lang.String responseEntity = "Your response entity";
                        // Log the successful status code and response entity
                        LOGGER.info("GET request (userMovie) successful with status code: 200, response entity: " + responseEntity);
                        return ResponseEntity.ok(movie);
                    } catch (Exception e) {
                        // Log the error message and status code
                        LOGGER.severe("GET request (userMovie) failed with status code: 500, error: " + e.getMessage());
                    }


        return ResponseEntity.notFound().build();
    }
    @GetMapping("/users/{userName}/movieList")
    public ResponseEntity<Set<Movie>> getMovies(@PathVariable(value = "userName") java.lang.String userName) {
        Set<Movie> movieSet = userService.getMovies(userName);
        try {
            // Define your response entity
            java.lang.String responseEntity = "Your response entity";
            // Log the successful status code and response entity
            LOGGER.info("GET request (getMovies) successful with status code: 200, response entity: " + responseEntity);
        } catch (Exception e) {
            // Log the error message and status code
            LOGGER.severe("GET request (getMovies) failed with status code: 500, error: " + e.getMessage());
        }
            return ResponseEntity.ok(movieSet);
    }



    @PutMapping("/users/{userName}")
    public ResponseEntity<java.lang.String> update (@PathVariable(value="userName") java.lang.String userName, @RequestBody Movie movie) {
        return ResponseEntity.ok(userService.update(userName, movie));
    }
    @PutMapping("/users/{userName}/movieList")
    public ResponseEntity<java.lang.String> addMovie(@PathVariable(value="userName") java.lang.String userName, @RequestBody Movie movie) {
        User user = userService.findByName(userName);
        Set<Movie> movieSet = user.getMovies();
        movieSet.add(movie);
        return ResponseEntity.ok(userService.addMovie(userName, movie));
    }

    @DeleteMapping("users/{userName}")
    public ResponseEntity<java.lang.String> delete (@PathVariable(value = "userName") java.lang.String userName) {
        return ResponseEntity.ok(userService.delete(userName));
    }
    @DeleteMapping("users/{userName}/movieList/{id}")
    public ResponseEntity<java.lang.String> deleteMovie(@PathVariable(value = "userName") java.lang.String userName,
                                                        @PathVariable(value = "id") java.lang.String stringId) {
        User user = userService.findByName(userName);
        if (user == null) throw new RuntimeException("User not found");
        Set<Movie> movies = user.getMovies();
        movies.removeIf(movie -> movie.getId().equals(stringId));
        user.setMovieSet(movies);
        userService.save(user);
        return ResponseEntity.ok().build();

    }



}
