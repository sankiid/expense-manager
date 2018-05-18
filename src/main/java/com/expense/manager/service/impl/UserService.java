package com.expense.manager.service.impl;

import com.expense.manager.bo.Role;
import com.expense.manager.bo.User;
import com.expense.manager.cache.RoleCache;
import com.expense.manager.dao.IUserDao;
import com.expense.manager.enums.UserRole;
import com.expense.manager.exception.UserNotFoundException;
import com.expense.manager.security.JwtTokenHandler;
import com.expense.manager.service.IUserService;
import com.expense.manager.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUser(String username, String password) throws UserNotFoundException {
        User user = userDao.getUserByUsername(username);
        if(user == null) {
            throw new UserNotFoundException("Invalid user name. User is not registered");
        }
        if(!user.getPassword().equals(StringUtils.getMD5Hash(password))){
            throw new UserNotFoundException("Incorrect password");
        }
        List<? extends GrantedAuthority> roles = userDao.getRoleByUserId(user.getUserId());
        user.setRoles(roles);
        String token = JwtTokenHandler.getInstance().generate(user);
        if(token == null){
            throw new UserNotFoundException("Incorrect credentials. Token generation failed");
        }
        user.setToken(token);
        return user;
    }

    @Override
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(StringUtils.getMD5Hash(user.getPassword()));
        List<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(UserRole.USER.getName());
        user.setRoles(roles);

        long userId = userDao.createUser(user);
        user.getAuthorities().forEach(r -> {
            userDao.createRole(userId, RoleCache.getInstance().getByName(r.getAuthority()).getId());
        });
    }

    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    public Role getRoleById(long id) {
        return userDao.getRoleById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

}
