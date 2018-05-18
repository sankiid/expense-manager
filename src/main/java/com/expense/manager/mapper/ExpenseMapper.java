package com.expense.manager.mapper;

import com.expense.manager.bo.Expense;
import com.expense.manager.bo.Income;
import com.expense.manager.cache.CategoryCache;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ExpenseMapper implements RowMapper<Expense> {

    private CategoryCache categoryCache = CategoryCache.getInstance();

    @Override
    public Expense mapRow(ResultSet rs, int i) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getLong("id"));
        expense.setAmount(rs.getFloat("amount"));
        expense.setNotes(rs.getString("notes"));
        expense.setCategory(categoryCache.getById(rs.getLong("category_id")));
        expense.setDate(rs.getDate("created_at"));
        return expense;
    }
}
