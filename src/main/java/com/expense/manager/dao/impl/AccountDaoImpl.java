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

    private static final String INSERT_SQL = "INSERT INTO user_account_info (amount, user_id, bank_id, account_number, created_at, updated_at) values (?, ?, ?, ?, now(), now())";
    private static final String UPDATE_SQL = "UPDATE user_account_info SET amount =?, bank_id=?, account_number=?, updated_at=now() WHERE id = ? and user_id = ?";
    private static final String GET_BY_ID_SQL = "SELECT * FROM user_account_info WHERE id = ? and user_id = ?";
    private static final String GET_BY_USERID_SQL = "SELECT * FROM user_account_info WHERE user_id = ?";
    private static final String DELETE_SQL = "DELETE FROM user_account_info WHERE id = ? and user_id = ?";
    private static final String UPDATE_AMOUNT_SQL = "UPDATE user_account_info SET amount = amount + ? WHERE id = ? and user_id = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long save(UserAccountInfo accountInfo, long userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
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

        jdbcTemplate.update(UPDATE_SQL, accountInfo.getAmount(), accountInfo.getBank().getId(), accountInfo.getAccountNumber(), accountInfo.getId(), userId);
    }

    @Override
    public UserAccountInfo get(long id, long userId) {
        return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new AccountInfoMapper(), id, userId);
    }

    @Override
    public List<UserAccountInfo> get(long userId) {
        return jdbcTemplate.query(GET_BY_USERID_SQL, new AccountInfoMapper(), userId);
    }

    @Override
    public void delete(long id, long userId) {

        jdbcTemplate.update(DELETE_SQL, id, userId);
    }

    @Override
    public void updateAmount(float amount, long id, long userId) {

        jdbcTemplate.update(UPDATE_AMOUNT_SQL, amount, id, userId);
    }

}
