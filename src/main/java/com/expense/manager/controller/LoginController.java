package com.expense.manager.controller;

import com.expense.manager.bo.Role;
import com.expense.manager.bo.User;
import com.expense.manager.cache.RoleCache;
import com.expense.manager.dto.Response;
import com.expense.manager.enums.UserRole;
import com.expense.manager.exception.UserNotFoundException;
import com.expense.manager.service.IUserService;
import com.expense.manager.utils.PasswordUtils;
import com.expense.manager.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Response login(@RequestBody User user) throws UserNotFoundException {
        user = userService.getUser(user.getUsername(), user.getPassword());
        user.setPassword(PasswordUtils.maskPassword(user.getPassword()));
        Response response = new Response(200, null, null, user);
        return response;
    }


    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public Response createUser(@RequestBody User user) throws UserNotFoundException {
        User dbUser = userService.getUserByUsername(user.getUsername());
        if(dbUser != null){
            throw new RuntimeException("User already registered");
        }
        userService.save(user);
        Response response = new Response(201, "successfully created new user", null, "{}");
        return response;
    }


}
