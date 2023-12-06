package com.yana.movies.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.xspec.M;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@DynamoDBTable(tableName = "users")
public class User {
@DynamoDBHashKey(attributeName = "userName")
    private String userName;
@DynamoDBAttribute(attributeName = "movieList")
    private Set<Movie> movies;

    public User() {
        this.movies = new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Movie> getMovies(String userName) {
        return movies;
    }
    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }
    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(userName, user.userName) && Objects.equals(movies, user.movies);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userName, movies);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", movies=" + movies +
                '}';
    }
}
