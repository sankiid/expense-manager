package com.expense.manager.dao.impl;

import com.expense.manager.bo.Category;
import com.expense.manager.dao.ICategoryDao;
import com.expense.manager.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CategoryDao implements ICategoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> getAllIncomeCategory() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryMapper());
    }

    @Override
    public Category getCategoryByName(String name) {
        String sql = "SELECT * FROM category WHERE name = ?";
        try{
            return jdbcTemplate.queryForObject(sql, new CategoryMapper(), name);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Category getCategoryById(long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        try{
            return jdbcTemplate.queryForObject(sql, new CategoryMapper(), id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public long save(Category category) {
        String sql = "INSERT INTO category (name, created_at, type) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, category.getName());
                pst.setDate(2, new Date(category.getCreatedAt().getTime()));
                pst.setString(3, category.getType().getName());
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
