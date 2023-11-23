package com.yana.movies.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
@DynamoDBTable(tableName = "users")
public class User {
@DynamoDBHashKey
    String userName;
@DynamoDBAttribute
    List<Movie> movies;
@DynamoDBAttribute
    Map<String, List<Movie>> allUsers;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Map<String, List<Movie>> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(Map<String, List<Movie>> allUsers) {
        this.allUsers = allUsers;
    }
}
