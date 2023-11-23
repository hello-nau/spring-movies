package com.yana.movies.entities;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

@Data
@DynamoDBTable(tableName = "movies")
public class Movie {
@DynamoDBHashKey
    String id; //movieName

@DynamoDBAttribute
    String description;

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getId() {
        return id;
    }
}
