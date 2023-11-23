package com.yana.movies.controllers;

import com.yana.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yana.movies.entities.Movie;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/movies") //edited 23nov
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.save(movie));
    }
    @GetMapping("{id}")
    public ResponseEntity<Movie> findByName(@PathVariable(value = "id") String id) {
        Movie movie = movieService.findById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }
    @PutMapping("{id}")
    public ResponseEntity<Movie> update(@PathVariable(value="id")String movieName,
                                        @RequestBody Movie movieToUpdate) {
        Movie existingMovie = movieService.findById(movieName);
        if (existingMovie != null) {
            existingMovie.setId(movieToUpdate.getId());
            existingMovie.setDescription(movieToUpdate.getDescription());
            Movie updated = movieService.save(existingMovie);
            return ResponseEntity.ok(updated);
        } else {
           return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(value="id") String id) {
        return ResponseEntity.ok(movieService.delete(id));
    }

}
