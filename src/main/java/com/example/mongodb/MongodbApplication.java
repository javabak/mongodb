package com.example.mongodb;

import com.example.mongodb.entity.Address;
import com.example.mongodb.entity.Gender;
import com.example.mongodb.entity.User;
import com.example.mongodb.repository.SearchRepository;
import com.example.mongodb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(
            SearchRepository searchRepository,
            UserRepository repository,
            MongoTemplate mongoTemplate
    ) {

        return args -> {
            repository.deleteAll();
            Address address = new Address("moscow", "1234");

            String lastname = "thaicuk";
            String otherLastname = "aliev";

            User user = new User(
                    "alim",
                    lastname,
                    address,
                    Gender.MALE,
                    BigDecimal.TEN,
                    List.of("Computer Science")
            );

            User otherUser = new User(
                    "ali",
                    otherLastname,
                    address,
                    Gender.MALE,
                    BigDecimal.ONE,
                    List.of("Math")
            );

            repository.insert(otherUser);
            searchRepository.insertOne();
        };
    }

    private static void usingMongoTemplateAndQuery(UserRepository repository, MongoTemplate mongoTemplate, String lastname, User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastname").is(lastname));

        List<User> users = mongoTemplate.find(query, User.class);

        if (users.size() > 1) {
            throw new IllegalStateException("found many users with lastname " + lastname);
        }

        if (users.isEmpty()) {
            System.out.println("inserting user " + user);
            repository.insert(user);
        } else {
            System.out.println(user + " already exists");
        }
    }

}
