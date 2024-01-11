package com.yana.movies.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
@DynamoDBTable(tableName = "users")
public class User {
@DynamoDBHashKey(attributeName = "userName")
    private String userName;
@DynamoDBAttribute(attributeName = "movieList")
    private List<String> movieSet;

    public User() {
        this.movieSet = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getMovieSet() {
        if (movieSet == null) movieSet = new ArrayList<>();
        return movieSet;
    }
    @DynamoDBIgnore
    public List<Movie> getMovies() {
        if(movieSet == null) movieSet = new ArrayList<>();
        return movieSet.stream()
                .map(json -> new Gson().fromJson(json, Movie.class))
                .collect(Collectors.toList());
    }

    public boolean addMovie(Movie movie) {
        String json = new Gson().toJson(movie);
        return movieSet.add(json);
    }
    public void setMovieSet(List<String> movieSet) {
        this.movieSet = movieSet;
    }
    public void setMovieSet2(List<Movie> movies) {
        if(movies == null) {
            this.movieSet = new ArrayList<>();
            movieSet.add("");
        } else {
            this.movieSet = new ArrayList<>();
            for (Movie m : movies) {
                Gson gson = new Gson();
                String movieStr = gson.toJson(m);
                movieSet.add(movieStr);
            }
//            this.movieSet = movies.stream()
//                    .map(movie -> new Gson().toJson(movie).toString())
//                    .collect(Collectors.toSet());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userName, user.userName) && Objects.equals(movieSet, user.movieSet);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userName, movieSet);
    }

    @Override
    public java.lang.String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", movies=" + movieSet +
                '}';
    }
}
