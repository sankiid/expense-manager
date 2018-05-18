package com.expense.manager.dao.impl;

import com.expense.manager.bo.Expense;
import com.expense.manager.bo.Income;
import com.expense.manager.dao.IExpenseDao;
import com.expense.manager.dao.IIncomeDao;
import com.expense.manager.mapper.ExpenseMapper;
import com.expense.manager.mapper.IncomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ExpenseDao implements IExpenseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long save(Expense expense, long userId) {
        String sql = "INSERT INTO expense (amount, created_at, category_id, notes, user_id) values (?, ?, ?,?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setFloat(1, expense.getAmount());
                pst.setDate(2, new Date(expense.getDate().getTime()));
                pst.setLong(3, expense.getCategory().getId());
                pst.setString(4, expense.getNotes());
                pst.setLong(5, userId);
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Expense expense, long userId) {
        String sql = "UPDATE expense set amount =?,created_at=?,category_id=?, notes=? WHERE id = ? and user_id = ?";
        jdbcTemplate.update(sql, expense.getAmount(), new Date(expense.getDate().getTime()), expense.getCategory().getId(), expense.getNotes(), expense.getId(), userId);
    }

    @Override
    public void delete(long id, long userId) {
        String sql = "DELETE FROM expense WHERE id = ? and user_id = ?";
        jdbcTemplate.update(sql, id, userId);
    }

    @Override
    public Expense get(long id, long userId) {
        String sql = "SELECT * FROM expense WHERE id = ? and user_id = ?";
        return jdbcTemplate.queryForObject(sql, new ExpenseMapper(), id, userId);
    }

    @Override
    public List<Expense> getByDate(Date start, Date end, long userId) {
        String sql = "SELECT * FROM expense WHERE user_id = ? and created_at between ? and ?";
        return jdbcTemplate.query(sql, new ExpenseMapper(), userId, start, end);
    }
}
