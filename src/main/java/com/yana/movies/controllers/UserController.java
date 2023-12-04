package com.yana.movies.controllers;

import com.yana.movies.entities.Movie;
import com.yana.movies.entities.User;

import java.util.HashSet;
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

    @GetMapping("/users/{userName}")
    public ResponseEntity<User> findByName(@PathVariable(value = "userName") String userName) {
        User user = userService.findByName(userName);
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/users/{userName}/{movieList}/")
    public ResponseEntity<Set<Movie>> getMovies(@PathVariable(value = "userName") String userName) {
        Set<Movie> movieSet = userService.getMovies(userName);
        System.out.println("We're in getMovies of user controller");
        try {
            // Define your response entity
            String responseEntity = "Your response entity";
            // Log the successful status code and response entity
            LOGGER.info("GET request successful with status code: 200, response entity: " + responseEntity);
        } catch (Exception e) {
            // Log the error message and status code
            LOGGER.severe("GET request failed with status code: 500, error: " + e.getMessage());
        }
            return ResponseEntity.ok(movieSet);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll () {
        List<User> allUsers = new ArrayList<>();
        allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @PutMapping("/users/{userName}")
    public ResponseEntity<String> update (@PathVariable(value="userName") String userName, @RequestBody Movie movie) {
        return ResponseEntity.ok(userService.update(userName, movie));
    }
    @PutMapping("/users/{userName}/movieList")
    public ResponseEntity<String> addMovie(@PathVariable(value="userName")String userName, @RequestBody Movie movie) {
        System.out.println("We are in addMovie userController.");
        User user = userService.findByName(userName);
        Set<Movie> movieSet = user.getMovies();
        movieSet.add(movie);
        System.out.println("We are in addMovie after attempting to add a movie to the movie set.");
        try {
            // Define your response entity
            String responseEntity = "Your response entity";
            // Log the successful status code and response entity
            LOGGER.info("GET request successful with status code: 200, response entity: " + responseEntity);
        } catch (Exception e) {
            // Log the error message and status code
            LOGGER.severe("GET request failed with status code: 500, error: " + e.getMessage());
        }
        return ResponseEntity.ok(userService.addMovie(userName, movie));
    }

    @DeleteMapping("users/{userName}")
    public ResponseEntity<String> delete (@PathVariable(value = "userName") String userName) {
        return ResponseEntity.ok(userService.delete(userName));
    }


}
