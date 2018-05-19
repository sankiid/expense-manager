package com.expense.manager.dao.impl;

import com.expense.manager.bo.Bank;
import com.expense.manager.dao.IBankDao;
import com.expense.manager.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
