package com.andrii.simplespringtodolist.services;

import com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects.TodoPojo;
import com.andrii.simplespringtodolist.domain.Tag;
import com.andrii.simplespringtodolist.domain.Todo;
import com.andrii.simplespringtodolist.domain.User;
import com.andrii.simplespringtodolist.exception.CustomEmptyDataException;
import com.andrii.simplespringtodolist.repositories.TodoRepository;
import com.andrii.simplespringtodolist.repositories.UserRepository;
import com.andrii.simplespringtodolist.services.interfaces.ITagService;
import com.andrii.simplespringtodolist.services.interfaces.ITodoService;
import com.andrii.simplespringtodolist.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {

    private final Converter converter;
    private final ITagService tagService;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(Converter converter,
                       ITagService tagService,
                       TodoRepository todoRepository,
                       UserRepository userRepository) {
        this.converter = converter;
        this.tagService = tagService;
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public TodoPojo createTodo(Todo todo, Long userId) {

        Optional<User> todoUserOptional = userRepository.findById(userId);

        if (todoUserOptional.isPresent()) {

            Set<Tag> tags = new HashSet<>(todo.getTagList());

            todo.getTagList().clear();

            todo.setUser(todoUserOptional.get());
            todoRepository.save(todo);

            tags.stream().map(tagService::findOrCreate).collect(Collectors.toSet()).forEach(todo::addTag);

            return converter.todoToPojo(todo);
        } else {
            throw new CustomEmptyDataException("unable to get user for todo");
        }
    }

    @Override
    @Transactional
    public TodoPojo getTodo(Long id, Long userId) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return converter.todoToPojo(todoOptional.get());
        } else {
            throw new NoSuchElementException("unable to get todo");
        }
    }

    @Override
    @Transactional
    public TodoPojo updateTodo(Todo source, Long todoId, Long userId) {

        Optional<Todo> targetOptional = todoRepository.findById(todoId);

        if (targetOptional.isPresent()) {
            Todo target = targetOptional.get();

            target.setName(source.getName());
            target.setComment(source.getComment());
            target.setStartDate(source.getStartDate());
            target.setEndDate(source.getEndDate());
            target.setImportant(source.getImportant());
            target.setPriority(source.getPriority());

            todoRepository.save(target);

            return converter.todoToPojo(target);
        } else {
            throw new NoSuchElementException("unable to update todo");
        }
    }

    @Override
    @Transactional
    public String deleteTodo(Long id, Long userId) {

        Optional<Todo> todoForDeleteOptional = todoRepository.findById(id);
        if(todoForDeleteOptional.isPresent()) {
            Todo todoForDelete = todoForDeleteOptional.get();
            new ArrayList<>(todoForDelete.getTagList()).forEach(tag -> tag.removeTodo(todoForDelete));
            todoRepository.delete(todoForDelete);
            return "Todo with id:" + id + " was successfully removed";
        } else {
            throw new NoSuchElementException("unable to delete todo");
        }
    }

    @Override
    @Transactional
    public List<TodoPojo> getAllTodos(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return todoRepository.findAllByUser(userOptional.get()).stream().map(converter::todoToPojo).collect(Collectors.toList());
        } else {
            throw new NoSuchElementException("No user with id: " + userId + " was found");
        }
    }
}
