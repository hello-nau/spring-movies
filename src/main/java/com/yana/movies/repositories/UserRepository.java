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

import java.util.List;

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
    public String update(String userName, User user) {
        mapper.save(user, new DynamoDBSaveExpression()
                .withExpectedEntry("movieName", new ExpectedAttributeValue(
                        new AttributeValue().withS(userName)
                )));
        return "Successfully updated Movie" + userName;
    }
    public String delete(String userName) {
        Movie movieToDelete = mapper.load(Movie.class, userName);
        mapper.delete(movieToDelete);
        return "Successfully deleted movie" + userName;
    }

}
