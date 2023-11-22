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
}
