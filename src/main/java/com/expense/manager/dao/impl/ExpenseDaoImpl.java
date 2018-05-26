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
public class ExpenseDaoImpl implements IExpenseDao {

    private static final String INSERT_SQL = "INSERT INTO expense (amount, created_at, category_id, notes, user_id, user_account_id) values (?, ?, ?,?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE expense set amount =?,created_at=?,category_id=?, notes=?, user_account_id = ? WHERE id = ? and user_id = ?";
    private static final String DELETE_SQL = "DELETE FROM expense WHERE id = ? and user_id = ?";
    private static final String GET_BY_ID_SQL = "select inc.id, inc.amount,inc.`category_id`, inc.`created_at`, inc.notes, b.id as bank_id, uai.`account_number`, uai.`amount` as account_amount, uai.id as account_id from `expense` inc inner join `user_account_info` uai on inc.`user_account_id` = uai.id inner join `bank` b on uai.`bank_id` = b.id where inc.id = ? and inc.user_id = ?";
    private static final String GET_BY_DATE_SQL = "select inc.id, inc.amount,inc.`category_id`, inc.`created_at`, inc.notes, b.id as bank_id, uai.`account_number`, uai.`amount` as account_amount, uai.id as account_id from `expense` inc inner join `user_account_info` uai on inc.`user_account_id` = uai.id inner join `bank` b on uai.`bank_id` = b.id where inc.user_id = ? and inc.created_at between ? and ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long save(Expense expense, long userId) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pst = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setFloat(1, expense.getAmount());
                pst.setDate(2, new Date(expense.getDate().getTime()));
                pst.setLong(3, expense.getCategory().getId());
                pst.setString(4, expense.getNotes());
                pst.setLong(5, userId);
                pst.setLong(6, expense.getAccount().getId());
                return pst;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Expense expense, long userId) {

        jdbcTemplate.update(UPDATE_SQL, expense.getAmount(), new Date(expense.getDate().getTime()), expense.getCategory().getId(), expense.getNotes(), expense.getAccount().getId(), expense.getId(), userId);
    }

    @Override
    public void delete(long id, long userId) {

        jdbcTemplate.update(DELETE_SQL, id, userId);
    }

    @Override
    public Expense get(long id, long userId) {

        return jdbcTemplate.queryForObject(GET_BY_ID_SQL, new ExpenseMapper(), id, userId);
    }

    @Override
    public List<Expense> getByDate(Date start, Date end, long userId) {

        return jdbcTemplate.query(GET_BY_DATE_SQL, new ExpenseMapper(), userId, start, end);
    }
}
