package com.expense.manager.mapper;

import com.expense.manager.bo.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankMapper implements RowMapper<Bank> {
    @Override
    public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {

        Bank bank = new Bank();
        bank.setId(rs.getLong("id"));
        bank.setName(rs.getString("name"));
        bank.setCreatedAt(rs.getDate("created_at"));
        bank.setUpdatedAt(rs.getDate("updated_at"));
        return bank;
    }
}
