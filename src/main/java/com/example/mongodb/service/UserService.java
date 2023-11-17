package com.example.mongodb.service;

import com.example.mongodb.entity.User;
import com.example.mongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    public User save(User user) {
        userRepository.insert(user);
        return user;
    }

    public Optional<User> getUser(String id) {
        return Optional.of(userRepository.findUserById(id).get());
    }

    public void deleteUser(String id) {
        Optional<User> user = userRepository.findUserById(id);
        userRepository.delete(user.get());
    }

    public List<User> deleteAllUsers() {
        List<User> users = userRepository.findAll();
        userRepository.deleteAll();
        return users;
    }
}
