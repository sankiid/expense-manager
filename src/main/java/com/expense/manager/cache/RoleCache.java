package com.expense.manager.cache;

import com.expense.manager.bo.Role;
import com.expense.manager.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleCache {

    private Map<Long, Role> idToNameCache = new HashMap<>();
    private Map<String, Role> nameToIdCache = new HashMap<>();
    private static RoleCache roleCache = new RoleCache();
    private IUserService userService;


    private RoleCache(){
    }

    public void init(IUserService userService) {
        if(this.userService == null){
            this.userService = userService;
        }
    }

    public static RoleCache getInstance(){
        return roleCache;
    }

    public void loadAll() {
        List<Role> roles = userService.getAllRoles();
        roles.forEach(c -> {
            setValues(c);
        });
    }


    public void setValues(Role role) {
        idToNameCache.put(role.getId(), role);
        nameToIdCache.put(role.getName(), role);
    }

    public Role getByName(String name){
        Role role = nameToIdCache.get(name);
        if(role == null){
            role = userService.getRoleByName(name);
            if(role != null){
                setValues(role);
            }
        }
        return role;
    }

    public Role getById(long id){
        Role role = idToNameCache.get(id);
        if(role == null){
            role = userService.getRoleById(id);
            if(role != null) {
                setValues(role);
            }
        }
        return role;
    }
}
