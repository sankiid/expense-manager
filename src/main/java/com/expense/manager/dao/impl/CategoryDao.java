package com.expense.manager.dao.impl;

import com.expense.manager.bo.Category;
import com.expense.manager.dao.ICategoryDao;
import com.expense.manager.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
