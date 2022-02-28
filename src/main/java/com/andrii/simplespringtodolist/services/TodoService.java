package com.andrii.simplespringtodolist.services;

import com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects.TodoPojo;
import com.andrii.simplespringtodolist.domain.Tag;
import com.andrii.simplespringtodolist.domain.Todo;
import com.andrii.simplespringtodolist.domain.User;
import com.andrii.simplespringtodolist.services.interfaces.ITagService;
import com.andrii.simplespringtodolist.services.interfaces.ITodoService;
import com.andrii.simplespringtodolist.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TodoService implements ITodoService {

    @PersistenceContext
    EntityManager entityManager;

    private final Converter converter;
    private final ITagService tagService;

    @Autowired
    public TodoService(Converter converter, ITagService tagService) {
        this.converter = converter;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public TodoPojo createTodo(Todo todo, Long userId) {

        User todoUser = entityManager
                .createQuery("SELECT user FROM User user WHERE user.id = :id", User.class)
                .setParameter("id", userId)
                .getSingleResult();
        todo.setUser(todoUser);

        Set<Tag> tags = new HashSet<>();
        tags.addAll(todo.getTagList());

        todo.getTagList().clear();

        entityManager.persist(todo);
        todo.setUser(todoUser);

        tags.stream().map(tag -> tagService.findOrCreate(tag)).collect(Collectors.toSet()).forEach(todo::addTag);

        return converter.todoToPojo(todo);
    }

    @Override
    public TodoPojo getTodo(long id) {
        return null;
    }

    @Override
    public List<TodoPojo> getAllTodos(Long userId) {
        return null;
    }

    @Override
    public TodoPojo updateTodo(Todo todo, long todoId) {
        return null;
    }

    @Override
    public TodoPojo deleteTodo(long id) {
        return null;
    }
}
