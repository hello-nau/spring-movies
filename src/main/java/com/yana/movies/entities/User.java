package com.yana.movies.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.xspec.M;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@DynamoDBTable(tableName = "users")
public class User {
@DynamoDBHashKey(attributeName = "userName")
    String userName;
@DynamoDBAttribute(attributeName = "movieList")
    Set<Movie> movies;

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
        System.out.println("We're in user getMovies");
        return movies;
    }
    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }
    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

}
