package com.yana.movies.converters;

import com.yana.movies.entities.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {
    public List<String> movieToList(Set<Movie> movies) {
        List<String> stringString = new ArrayList<>();
        stringString = movies.stream().map(Movie::toString).collect(Collectors.toList());
        return stringString;
    }
    public Movie stringToMovie(String stringStr) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String cleanJson = stringStr.trim().replace(" ", "");
//        Movie movie = objectMapper.readValue(cleanJson, Movie.class);
//        return movie;
        ObjectMapper objectMapper = new ObjectMapper();
        Movie movie = objectMapper.readValue(stringStr, Movie.class);
        return movie;
    }

}
