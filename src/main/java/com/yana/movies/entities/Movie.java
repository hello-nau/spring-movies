package com.yana.movies.entities;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.HashMap;

@Data
@DynamoDBTable(tableName = "movies")
public class Movie {
@DynamoDBHashKey
    String movieName; //id

@DynamoDBAttribute
    String description;

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getMovieName() {
        return movieName;
    }
}
