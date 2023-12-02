package com.yana.movies.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@DynamoDBTable(tableName = "users")
public class User {
@DynamoDBHashKey(attributeName = "userName")
    String userName;
@DynamoDBAttribute(attributeName = "description")
    String description;
@DynamoDBAttribute(attributeName = "movieList")
    Set<Movie> movies;
@DynamoDBAttribute(attributeName = "allUsers")
    Map<String, Set<Movie>> allUsers;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User() {
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

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Map<String, Set<Movie>> getAllUsers() {
        return allUsers;
    }

}
