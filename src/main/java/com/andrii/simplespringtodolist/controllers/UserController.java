package com.andrii.simplespringtodolist.controllers;

import com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects.UserPojo;
import com.andrii.simplespringtodolist.domain.User;
import com.andrii.simplespringtodolist.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<UserPojo> createUser(@RequestBody User user) {
        UserPojo result = userService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> getUser(@PathVariable Long id) {
        UserPojo result = userService.getUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserPojo>> getUser() {
        List<UserPojo> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

