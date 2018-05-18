package com.expense.manager.mapper;

import com.expense.manager.bo.Category;
import com.expense.manager.enums.Type;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int i) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));
        category.setCreatedAt(rs.getDate("created_at"));
        category.setType(Type.value(rs.getString("type")));
        return category;
    }
}
