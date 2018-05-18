package com.expense.manager.dao;

import com.expense.manager.bo.Role;
import com.expense.manager.bo.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface IUserDao {
    User getUserByUsername(String username);

    long createUser(User user);

    Role getRoleByName(String name);

    void createRole(long userId, long roleId);

    List<Role> getAllRoles();

    Role getRoleById(long id);

    List<? extends GrantedAuthority> getRoleByUserId(long userId);
}
