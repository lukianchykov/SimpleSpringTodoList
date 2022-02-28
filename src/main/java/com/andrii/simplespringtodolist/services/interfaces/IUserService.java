package com.andrii.simplespringtodolist.services.interfaces;

import com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects.UserPojo;
import com.andrii.simplespringtodolist.domain.User;

import java.util.List;

public interface IUserService {
    UserPojo createUser(User user);
    UserPojo getUser(long id);
    List<UserPojo> getAllUsers();
    UserPojo updateUser(User userUpdated, long id);
    UserPojo deleteUser(long id);

}