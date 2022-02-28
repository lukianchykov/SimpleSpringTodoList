package com.andrii.simplespringtodolist.services.interfaces;

import com.andrii.simplespringtodolist.domain.PlainObjects.PlainObjects.UserPojo;
import com.andrii.simplespringtodolist.domain.User;

import java.util.List;

public interface IUserService {
    UserPojo createUser(User user);
    UserPojo findUserByEmailAndPassword(String email, String password);
    UserPojo getUser(long id);
    List<UserPojo> getAllUsers();
    UserPojo updateUser(User user, long id);
    String deleteUser(long id);

}