package com.expense.manager.mapper;

import com.expense.manager.bo.Income;
import com.expense.manager.cache.CategoryCache;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IncomeMapper implements RowMapper<Income> {

    private CategoryCache categoryCache = CategoryCache.getInstance();

    @Override
    public Income mapRow(ResultSet rs, int i) throws SQLException {
        Income income = new Income();
        income.setId(rs.getLong("id"));
        income.setAmount(rs.getFloat("amount"));
        income.setNotes(rs.getString("notes"));
        income.setCategory(categoryCache.getById(rs.getLong("category_id")));
        income.setDate(rs.getDate("created_at"));
        return income;
    }
}
