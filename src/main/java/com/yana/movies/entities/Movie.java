package com.yana.movies.entities;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.util.Objects;

//@Data
@DynamoDBTable(tableName = "movies")
public class Movie {
@DynamoDBHashKey(attributeName = "id")
String id; //movieName
@DynamoDBAttribute(attributeName = "description")
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
    public java.lang.String getId() {
        return id;
    }

    @Override
    public java.lang.String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
