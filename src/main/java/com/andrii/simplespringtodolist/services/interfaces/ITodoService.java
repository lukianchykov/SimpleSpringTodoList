package com.andrii.simplespringtodolist.services.interfaces;

import com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects.TodoPojo;
import com.andrii.simplespringtodolist.domain.Todo;

import java.util.List;

public interface ITodoService {
    TodoPojo createTodo(Todo todo, Long userId);
    TodoPojo getTodo(long id);
    TodoPojo updateTodo(Todo todo, long todoId);
    TodoPojo deleteTodo(long id);
    List<TodoPojo> getAllTodos(Long userId);
}
