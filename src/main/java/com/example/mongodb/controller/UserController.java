package com.example.mongodb.controller;

import com.example.mongodb.entity.User;
import com.example.mongodb.exception.NotFoundException;
import com.example.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = userService.getUser(id);

        return new ResponseEntity<>(
                user.orElseThrow(() ->
                        new NotFoundException("user not found: ".formatted(user))),
                HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> fetchAllUsers() {
        Optional<List<User>> users = Optional.ofNullable(userService.getAllStudents());
        return users.map(userList
                -> new ResponseEntity<>(userList, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<Object> createAndSaveUser(@RequestBody User user) {
        if (user != null) {
            User createdUser = userService.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
        }
        return new ResponseEntity<>(new NotFoundException("user not found".formatted(user)),
                HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<List<User>> deleteAllUsers() {
        return new ResponseEntity<>(userService.deleteAllUsers(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> update(
            @PathVariable String id,
            @RequestBody User updatesForUser) {

        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            user = Optional.of(new User(
                    updatesForUser.getFirstname(),
                    updatesForUser.getLastname(),
                    updatesForUser.getAddress(),
                    updatesForUser.getGender(),
                    updatesForUser.getTotalSpentInBooks(),
                    updatesForUser.getFavoriteSubjects()
            ));
            return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(updatesForUser, HttpStatus.NOT_FOUND);
    }
}
