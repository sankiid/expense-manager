package com.expense.manager.dao.impl;

import com.expense.manager.bo.Income;
import com.expense.manager.dao.IIncomeDao;
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
public class IncomeDao implements IIncomeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long save(Income income, long userId) {
        String sql = "INSERT INTO income (amount, created_at, category_id, notes, user_id) values (?, ?, ?,?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setFloat(1, income.getAmount());
                pst.setDate(2, new Date(income.getDate().getTime()));
                pst.setLong(3, income.getCategory().getId());
                pst.setString(4, income.getNotes());
                pst.setLong(5, userId);
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Income income, long userId) {
        String sql = "UPDATE income set amount =?,created_at=?,category_id=?, notes=? WHERE id = ? and user_id = ?";
        jdbcTemplate.update(sql, income.getAmount(), new Date(income.getDate().getTime()), income.getCategory().getId(), income.getNotes(), income.getId(), userId);
    }

    @Override
    public void delete(long id, long userId) {
        String sql = "DELETE FROM income WHERE id = ? and user_id = ?";
        jdbcTemplate.update(sql, id, userId);
    }

    @Override
    public Income get(long id, long userId) {
        String sql = "SELECT * FROM income WHERE id = ? and user_id = ?";
        return jdbcTemplate.queryForObject(sql, new IncomeMapper(), id, userId);
    }

    @Override
    public List<Income> getByDate(Date start, Date end, long userId) {
        String sql = "SELECT * FROM income WHERE user_id = ? and created_at between ? and ?";
        return jdbcTemplate.query(sql, new IncomeMapper(), userId, start, end);
    }
}
