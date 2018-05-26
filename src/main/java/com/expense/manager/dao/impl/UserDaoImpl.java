package com.expense.manager.dao.impl;

import com.expense.manager.bo.Role;
import com.expense.manager.bo.User;
import com.expense.manager.cache.RoleCache;
import com.expense.manager.dao.IUserDao;
import com.expense.manager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try{
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public long createUser(User user) {
        String sql = "INSERT INTO users (user_name, password, created_at, name) VALUES (?, ?, now(), ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, user.getUsername());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getName());
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();

    }

    @Override
    public void createRole(long userId, long roleId) {
        String sql = "INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, roleId);
    }

    @Override
    public List<Role> getAllRoles() {
        String sql = "SELECT * FROM roles";
        return jdbcTemplate.query(sql, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet rs, int i) throws SQLException {
                Role role = new Role(rs.getLong("id"), rs.getString("name"));
                return role;
            }
        });
    }

    @Override
    public Role getRoleById(long id) {
        String sql = "SELECT * FROM roles WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet rs, int i) throws SQLException {
                Role role = new Role(rs.getLong("id"), rs.getString("name"));
                return role;
            }
        }, id);
    }

    @Override
    public List<? extends GrantedAuthority> getRoleByUserId(long userId) {
        String sql = "select role_id from user_role where user_id = ?";
        List<String> roles = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return RoleCache.getInstance().getById(rs.getLong("role_id")).getName();
            }
        }, userId);
        if(roles == null){
            return null;
        }
        return AuthorityUtils.createAuthorityList(roles.toArray(new String[0]));
    }

    @Override
    public Role getRoleByName(String name) {
        String sql = "SELECT * FROM roles WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet rs, int i) throws SQLException {
                Role role = new Role(rs.getLong("id"), rs.getString("name"));
                return role;
            }
        }, name);
    }
}
