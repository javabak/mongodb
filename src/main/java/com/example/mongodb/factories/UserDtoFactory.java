package com.example.mongodb.factories;

import com.example.mongodb.dto.UserDto;
import com.example.mongodb.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoFactory {

    public UserDto makeUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .address(user.getAddress())
                .gender(user.getGender())
                .totalSpentInBooks(user.getTotalSpentInBooks())
                .favoriteSubjects(user.getFavoriteSubjects())
                .build();
    }
}
