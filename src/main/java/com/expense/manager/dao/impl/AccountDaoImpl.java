package com.expense.manager.dao.impl;

import com.expense.manager.bo.UserAccountInfo;
import com.expense.manager.dao.IAccountDao;
import com.expense.manager.mapper.AccountInfoMapper;
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
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long save(UserAccountInfo accountInfo, long userId) {
        String sql = "INSERT INTO user_account_info (amount, user_id, bank_id, account_number, created_at, update_at) values (?, ?, ?, ?, now(), now())";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setFloat(1, accountInfo.getAmount());
                pst.setLong(2, userId);
                pst.setLong(3, accountInfo.getBank().getId());
                pst.setString(4, accountInfo.getAccountNumber());
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(UserAccountInfo accountInfo, long userId) {
        String sql = "UPDATE user_account_info SET amount =?, bank_id=?, account_number=?, update_at=now() WHERE id = ? and user_id = ?";
        jdbcTemplate.update(sql, accountInfo.getAmount(), accountInfo.getBank().getId(), accountInfo.getAccountNumber(), accountInfo.getId(), userId);

    }

    @Override
    public UserAccountInfo get(long id, long userId) {
        String sql = "SELECT * FROM user_account_info WHERE id = ? and user_id = ?";
        return jdbcTemplate.queryForObject(sql, new AccountInfoMapper(), id, userId);
    }

    @Override
    public List<UserAccountInfo> get(long userId) {
        String sql = "SELECT * FROM user_account_info WHERE user_id = ?";
        return jdbcTemplate.query(sql, new AccountInfoMapper(), userId);
    }

    @Override
    public void delete(long id, long userId) {
        String sql = "DELETE FROM user_account_info WHERE id = ? and user_id = ?";
        jdbcTemplate.update(sql, id, userId);
    }
}
