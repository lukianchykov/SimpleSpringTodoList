package com.andrii.simplespringtodolist.controllers;

import com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects.TodoPojo;
import com.andrii.simplespringtodolist.domain.Todo;
import com.andrii.simplespringtodolist.services.interfaces.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final ITodoService todoService;

    @Autowired
    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping(path = "/user/{userId}/todo/create")
    public ResponseEntity<TodoPojo> createTodo(@RequestBody Todo todo, @PathVariable Long userId) {
        TodoPojo result = todoService.createTodo(todo, userId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
