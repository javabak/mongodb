package com.example.mongodb.repository;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import jakarta.annotation.PreDestroy;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SearchRepositoryImpl implements SearchRepository {


    private static final String URL = "mongodb://localhost:27017/local";

    private static final ConnectionString connectionString = new ConnectionString(URL);
    private static final char[] password = connectionString.getPassword();
    private final MongoClient mongoClient;

    MongoCollection<Document> mongoCollection = getDocumentMongoCollection();

    @Autowired
    public SearchRepositoryImpl(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @Override
    public void insertOne() {
        try {
            InsertOneResult insertUser = mongoCollection.insertOne(
                    new Document()
                            .append("childName", "alim")
                            .append("childId", "1")
                            .append("favoriteSubjects", Arrays.asList("math", "computer science"))
            );
            System.out.println("Success! Inserted User: " + insertUser.getInsertedId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private MongoCollection<Document> getDocumentMongoCollection() {
        assert mongoClient != null;
        MongoDatabase mongoDatabase = mongoClient.getDatabase("local");
        return mongoDatabase.getCollection("user");
    }

    @PreDestroy
    public void closeMongoDBConnection() {
        System.out.println(Arrays.toString(password) + "\n");
        mongoClient.close();
    }
}
