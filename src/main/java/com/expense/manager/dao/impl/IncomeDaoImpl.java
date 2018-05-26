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
public class IncomeDaoImpl implements IIncomeDao {

    private static final String INSERT_SQL = "INSERT INTO income (amount, created_at, category_id, notes, user_id, user_account_id) values (?, ?, ?,?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE income set amount =?,created_at=?,category_id=?, notes=?, user_account_id = ? WHERE id = ? and user_id = ?";
    private static final String DELETE_SQL = "DELETE FROM income WHERE id = ? and user_id = ?";
    private static final String GET_BY_ID_SQL = "select inc.id, inc.amount,inc.`category_id`, inc.`created_at`, inc.notes, b.id as bank_id, uai.`account_number`, uai.`amount` as account_amount, uai.id as account_id from `income` inc inner join `user_account_info` uai on inc.`user_account_id` = uai.id inner join `bank` b on uai.`bank_id` = b.id where inc.id = ? and inc.user_id = ?";
    private static final String GET_BY_DATE_SQL = "select inc.id, inc.amount,inc.`category_id`, inc.`created_at`, inc.notes, b.id as bank_id, uai.`account_number`, uai.`amount` as account_amount, uai.id as account_id from `income` inc inner join `user_account_info` uai on inc.`user_account_id` = uai.id inner join `bank` b on uai.`bank_id` = b.id where inc.user_id = ? and inc.created_at between ? and ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long save(Income income, long userId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setFloat(1, income.getAmount());
                pst.setDate(2, new Date(income.getDate().getTime()));
                pst.setLong(3, income.getCategory().getId());
                pst.setString(4, income.getNotes());
                pst.setLong(5, userId);
                pst.setLong(6, income.getAccount().getId());
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Income income, long userId) {
        jdbcTemplate.update(UPDATE_SQL, income.getAmount(), new Date(income.getDate().getTime()), income.getCategory().getId(), income.getNotes(), income.getAccount().getId(), income.getId(), userId);
    }

    @Override
    public void delete(long id, long userId) {
        jdbcTemplate.update(DELETE_SQL, id, userId);
    }

    @Override
    public Income get(long id, long userId) {
        return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new IncomeMapper(), id, userId);
    }

    @Override
    public List<Income> getByDate(Date start, Date end, long userId) {
        return jdbcTemplate.query(GET_BY_DATE_SQL, new IncomeMapper(), userId, start, end);
    }
}
