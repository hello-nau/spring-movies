package com.yana.movies.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.gson.Gson;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@DynamoDBTable(tableName = "users")
public class User {
@DynamoDBHashKey(attributeName = "userName")
    private java.lang.String userName;
@DynamoDBAttribute(attributeName = "movieList")
    private Set<String> movieSet;

    public User() {
        this.movieSet = new HashSet<>();
    }

    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    public Set<String> getMovieSet() {
        return movieSet;
    }
    public Set<Movie> getMovies() {
        return movieSet.stream()
                .map(json -> new Gson().fromJson(json, Movie.class))
                .collect(Collectors.toSet());
    }
    public boolean addMovie(Movie movie) {
        String json = new Gson().toJson(movie);
        return movieSet.add(json);
    }
    public void setMovieSet(Set<Movie> movies) {
        this.movieSet = movies.stream()
                .map(movie -> new Gson().toJson(movie))
                .collect(Collectors.toSet());
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
