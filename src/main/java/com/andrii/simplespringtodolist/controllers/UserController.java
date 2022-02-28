package com.andrii.simplespringtodolist.controllers;

import com.andrii.simplespringtodolist.domain.User;
import com.andrii.simplespringtodolist.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/create_table")
    public ResponseEntity<String> getTest() {
        userService.createTableUser();
        return new ResponseEntity<>("Table has been created", HttpStatus.OK);
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<Integer> createUser(@RequestBody User user) {
        int result = userService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User result = userService.getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path = "/user/update/{id}")
    public ResponseEntity<Integer> updateUser(@RequestBody User updatedUser, @PathVariable long id) {
        int result = userService.updateUser(updatedUser, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/delete/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable long id) {
        int result = userService.deleteUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
