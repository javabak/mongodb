package com.example.mongodb.dto;


import com.example.mongodb.entity.Address;
import com.example.mongodb.entity.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    String id;

    @NotNull
    @JsonProperty("first_name")
    String firstname;

    @NotNull
    @JsonProperty("last_name")
    String lastname;

    @NotNull
    Address address;

    @Enumerated(EnumType.STRING)
    Gender gender;

    BigDecimal totalSpentInBooks;
    List<String> favoriteSubjects;
}
