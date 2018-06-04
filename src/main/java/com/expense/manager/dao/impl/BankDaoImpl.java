package com.expense.manager.dao.impl;

import com.expense.manager.bo.Bank;
import com.expense.manager.dao.IBankDao;
import com.expense.manager.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class BankDaoImpl implements IBankDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bank> getAllBanks() {
        String sql = "SELECT * FROM bank";
        return jdbcTemplate.query(sql, new BankMapper());
    }

    @Override
    public Bank getBankById(long id) {
        String sql = "SELECT * FROM bank where id = ?";
        return jdbcTemplate.queryForObject(sql, new BankMapper(), id);
    }

    @Override
    public long save(Bank bank) {
        String INSERT_SQL = "INSERT INTO bank(name,created_at,updated_at) VALUES (?,now(),now())";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, bank.getName());
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
