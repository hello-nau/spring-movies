package com.yana.movies.controllers;

import com.yana.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yana.movies.entities.Movie;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    MovieService movieService;
    @RequestMapping(value="/movies", produces="application/json")
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.save(movie));
    }
    @GetMapping("{id}")
    public ResponseEntity<Movie> findByName(@PathVariable(value = "id") java.lang.String id) {
        Movie movie = movieService.findById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> update(@PathVariable(value="id") java.lang.String id,
                                        @RequestBody Movie movieToUpdate) {
        Movie existingMovie = movieService.findById(id);
        if (existingMovie != null) {
            if(existingMovie.getId().equals(movieToUpdate.getId())) {
                existingMovie.setDescription(movieToUpdate.getDescription());
                Movie updated = movieService.save(existingMovie);
                return ResponseEntity.ok(updated);
            } else  {
                movieService.delete(existingMovie.getId());
                movieService.save(movieToUpdate);
            }
        }
        return ResponseEntity.ok(movieToUpdate);
    }
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<java.lang.String> delete(@PathVariable(value="id") java.lang.String id) {
        return ResponseEntity.ok(movieService.delete(id));
    }

}
