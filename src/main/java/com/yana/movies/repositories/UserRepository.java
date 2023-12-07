package com.yana.movies.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.yana.movies.entities.Movie;
import com.yana.movies.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    @Autowired
    private DynamoDBMapper mapper;
    public User save(User user) {
        mapper.save(user);
        return mapper.load(User.class, user.getUserName());
    }
    public User findByName(java.lang.String userName) {
        return mapper.load(User.class, userName);
    }
    public List<User> findAll() {
        return mapper.scan(User.class, new DynamoDBScanExpression());
    }
    public java.lang.String update(java.lang.String userName, Movie movie) {
        User user = findByName(userName);
        Set<Movie> movieSet = user.getMovies();
        movieSet.add(movie);
        mapper.save(user, new DynamoDBSaveExpression()
                .withExpectedEntry("userName", new ExpectedAttributeValue(
                        new AttributeValue().withS(userName)
                )));
        return "Successfully updated Movie for " + userName;
    }

    public java.lang.String delete(java.lang.String userName) {
        User user = mapper.load(User.class, userName);
        mapper.delete(user);
        return "Successfully deleted user" + userName;
    }

    public java.lang.String addMovie (java.lang.String userName, Movie movie)  {
        User user = mapper.load(User.class, userName);
        if (!user.addMovie(movie)) {
            return "The movie " + movie + " already exists in the list.";
        }
        user.addMovie(movie);
        mapper.save(user, new DynamoDBSaveExpression()
                .withExpectedEntry("userName", new ExpectedAttributeValue(
                        new AttributeValue().withS(userName)
                )));
        return "The movie " + movie.getId() + " added successfully to user " + userName;
    }
    public java.lang.String deleteMovie (java.lang.String userName, Movie movieToDelete) {
        User user = mapper.load(User.class, userName);
        Set<Movie> movieSet = user.getMovies();
        if(movieSet.contains(movieToDelete)) {
            movieSet.remove(movieToDelete);
        } else {
            return "The " + movieToDelete.getId() + " is not present in the list.";
        }
        mapper.save(user, new DynamoDBSaveExpression()
                .withExpectedEntry("userName", new ExpectedAttributeValue(
                        new AttributeValue().withS(userName)
                )));
        return "The movie " + movieToDelete.getId() + " was deleted successfully.";
    }

    public Set<Movie> getMovies(String userName) {
        User user = mapper.load(User.class, userName);
        Set<Movie> movieSet = user.getMovies();
        return movieSet;
    }

}
