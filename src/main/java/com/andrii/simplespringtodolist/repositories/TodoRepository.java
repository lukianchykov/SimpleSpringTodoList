package com.andrii.simplespringtodolist.repositories;

import com.andrii.simplespringtodolist.domain.Todo;
import com.andrii.simplespringtodolist.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
}
