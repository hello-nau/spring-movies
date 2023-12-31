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
        if (user.getMovieSet().isEmpty()) {
            user.setMovieSet(null);
        }
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
        List<String> movieSet = user.getMovieSet();
        user.addMovie(movie);
        mapper.save(user, new DynamoDBSaveExpression()
                .withExpectedEntry("userName", new ExpectedAttributeValue(
                        new AttributeValue().withS(userName)
                )));
        return "Successfully updated Movie for " + userName;
    }

    public String delete(String userName) {
        User user = mapper.load(User.class, userName);
        mapper.delete(user);
        return "Successfully deleted user" + userName;
    }

    public String addMovie (String userName, Movie movie)  {
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
    public String deleteMovie (String userName, Movie movieToDelete) {
        User user = mapper.load(User.class, userName);
        List<Movie> movieSet = user.getMovies();
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

    public List<Movie> getMovies(String userName) {
        User user = mapper.load(User.class, userName);
        return user.getMovies();
    }

}
