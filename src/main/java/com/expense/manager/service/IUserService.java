package com.expense.manager.service;

import com.expense.manager.bo.Role;
import com.expense.manager.bo.User;
import com.expense.manager.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    User getUserByUsername(String username);

    User getUser(String username, String password) throws UserNotFoundException;

    Role getRoleByName(String name);

    void save(User user);

    List<Role> getAllRoles();

    Role getRoleById(long id);
}
