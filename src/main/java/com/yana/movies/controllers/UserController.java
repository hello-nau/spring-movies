package com.yana.movies.controllers;

import com.yana.movies.converters.MovieConverter;
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
@CrossOrigin(origins = "http://127.0.0.1:5500")
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
            List<String> movieSet = user.getMovieSet();
            for (String s : movieSet) {
                System.out.println("for loop of what s in movieSet gives back: " + s);
            }
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/users/{userName}/movieList/{id}")
    public ResponseEntity<Movie> userMovie(@PathVariable(value = "userName") String userName,
                                           @PathVariable(value = "id") String movieId) throws Exception {
        User user = userService.findByName(userName);
        List<String> movieSet = user.getMovieSet();
        for (String s : movieSet) {
            Movie movie = new MovieConverter().stringToMovie(s);
            if (movie.getId().equals(movieId)) {
                String responseEntity = "Your response entity";
                LOGGER.info("GET request (userMovie) successful with status code: 200, response entity: " + responseEntity);
                return ResponseEntity.ok(movie);
            }
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/users/{userName}/movieList")
    public ResponseEntity<List<Movie>> getMovies(@PathVariable(value = "userName") java.lang.String userName) {
        List<Movie> movieSet = userService.getMovies(userName);
        try {
            // Define your response entity
            java.lang.String responseEntity = "getMovies()";
            // Log the successful status code and response entity
            LOGGER.info("GET request successful with status code: 200, response entity: " + responseEntity);
        } catch (Exception e) {
            // Log the error message and status code
            LOGGER.severe("GET request failed with status code: 500, error: " + e.getMessage());
        }
            return ResponseEntity.ok(movieSet);
    }



    @PutMapping("/users/{userName}")
    public ResponseEntity<java.lang.String> update (@PathVariable(value="userName") String userName, @RequestBody Movie movie) {
        return ResponseEntity.ok(userService.update(userName, movie));
    }
    @PutMapping("/users/{userName}/movieList")
    public ResponseEntity<String> addMovie(@PathVariable(value="userName") String userName, @RequestBody Movie movie) {
        User user = userService.findByName(userName);
        List<Movie> movieSet = user.getMovies();
        movieSet.add(movie);
        return ResponseEntity.ok(userService.addMovie(userName, movie));
    }

    @DeleteMapping("users/{userName}")
    public ResponseEntity<java.lang.String> delete (@PathVariable(value = "userName") String userName) {
        return ResponseEntity.ok(userService.delete(userName));
    }
    @DeleteMapping("users/{userName}/movieList/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(value = "userName") String userName,
                                                        @PathVariable(value = "id") String movieId) {
        User user = userService.findByName(userName);
        if (user == null) throw new RuntimeException("User not found");
        List<Movie> movies = user.getMovies();
        movies.removeIf(movie -> movie.getId().equals(movieId));
        user.setMovieSet2(movies);
        userService.save(user);
        return ResponseEntity.ok().build();

    }



}
