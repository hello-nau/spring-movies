package com.yana.movies.controllers;

import com.yana.movies.entities.Movie;
import com.yana.movies.entities.User;
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
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll () {
        List<User> allUsers = new ArrayList<>();
        allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @PutMapping("/users/{userName}")
    public ResponseEntity<User> update (@PathVariable(value="userName") String userName, @RequestBody Movie movie) {
        User user = userService.findByName(userName);
        if (user != null) {
            userService.addMovie(userName, movie);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/users/{userName}/movieList")
    public ResponseEntity<String> addMovie(@PathVariable(value="userName")String userName, @RequestBody Movie movie) {
        return ResponseEntity.ok(userService.addMovie(userName, movie));
    }

    @GetMapping("/users/{userName}/movieList")
    public ResponseEntity<Set<Movie>> getMovies(@PathVariable(value = "userName") String userName) {
        return ResponseEntity.ok(userService.getMovies(userName));
    }


    @DeleteMapping("users/{userName}")
    public ResponseEntity<String> delete (@PathVariable(value = "userName") String userName) {
        return ResponseEntity.ok(userService.delete(userName));
    }


}
