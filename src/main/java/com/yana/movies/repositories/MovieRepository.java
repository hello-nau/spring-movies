package com.yana.movies.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.yana.movies.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {
    @Autowired
    private DynamoDBMapper mapper;
    public Movie save(Movie movie) {
        mapper.save(movie);
//        return mapper.load(Movie.class, movie.getId());
        return movie;
    }
    public Movie findByName(java.lang.String stringName) {
        return mapper.load(Movie.class, stringName);
    }
    public List<Movie> findAll() {
        return mapper.scan(Movie.class, new DynamoDBScanExpression());
    }
    public java.lang.String update(java.lang.String id, Movie movie) {
        mapper.save(movie, new DynamoDBSaveExpression()
                .withExpectedEntry("id", new ExpectedAttributeValue(
                        new AttributeValue().withS(id)
                )));
        return "Successfully updated Movie" + id;
    }

    public java.lang.String delete(java.lang.String stringName) {
        Movie movieToDelete = mapper.load(Movie.class, stringName);
        mapper.delete(movieToDelete);
        return "Successfully deleted movie " + stringName;
    }

}
