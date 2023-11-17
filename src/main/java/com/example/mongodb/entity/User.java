package com.example.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    @NotNull
    @JsonProperty("first_name")
    String firstname;


    @Indexed(unique = true)
    @NotNull
    @JsonProperty("last_name")
    String lastname;

    @NotNull
    Address address;

    @Enumerated(EnumType.STRING)
    Gender gender;

    BigDecimal totalSpentInBooks;
    List<String> favoriteSubjects;


    public User(String firstname,
                String lastname,
                Address address,
                Gender gender,
                BigDecimal totalSpentInBooks,
                List<String> favoriteSubjects) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.gender = gender;
        this.totalSpentInBooks = totalSpentInBooks;
        this.favoriteSubjects = favoriteSubjects;
    }
}
