package com.yana.movies.converters;

import com.yana.movies.entities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {
    public List<String> movieToList(Set<Movie> movies) {
        List<String> movieString = new ArrayList<>();
        movieString = movies.stream().map(Movie::toString).collect(Collectors.toList());
        return movieString;
    }

}
