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
    public User findByName(String userName) {
        return mapper.load(User.class, userName);
    }
    public List<User> findAll() {
        return mapper.scan(User.class, new DynamoDBScanExpression());
    }
    public String update(String userName, Movie movie) {
        User user = findByName(userName);
        Set<Movie> movieSet = user.getMovies();
        movieSet.add(movie);
        mapper.save(user, new DynamoDBSaveExpression()
                .withExpectedEntry("userName", new ExpectedAttributeValue(
                        new AttributeValue().withS(userName)
                )));
        return "Successfully updated Movie for " + userName;
    }

    public String delete(String userName) {
        Movie movieToDelete = mapper.load(Movie.class, userName);
        mapper.delete(movieToDelete);
        return "Successfully deleted movie" + userName;
    }

    public String addMovie (String userName, Movie movie)  {
        User user = mapper.load(User.class, userName);
        if (!user.addMovie(movie)) {
            return "The movie " + movie + " already exists in the list.";
        }
        user.addMovie(movie);
        return "The movie " + movie + " added successfully to user " + userName;
    }

    public Set<Movie> getMovies(String userName) {
        User user = mapper.load(User.class, userName);
        return user.getMovies();
    }

}
