package com.yana.movies.converters;

import com.yana.movies.entities.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {
    public List<java.lang.String> movieToList(Set<Movie> movies) {
        List<java.lang.String> stringString = new ArrayList<>();
        stringString = movies.stream().map(Movie::toString).collect(Collectors.toList());
        return stringString;
    }
    public Movie stringToMovie(java.lang.String stringStr) {
        if(!stringStr.startsWith("Movie(") && !stringStr.endsWith(")")) {
            throw new IllegalArgumentException("Wrong movie format.");
        }

        Movie movie = new Movie();
        return movie;

    }

}
