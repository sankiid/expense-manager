package com.expense.manager.mapper;

import com.expense.manager.bo.Bank;
import com.expense.manager.bo.Expense;
import com.expense.manager.bo.Income;
import com.expense.manager.bo.UserAccountInfo;
import com.expense.manager.cache.BankCache;
import com.expense.manager.cache.CategoryCache;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ExpenseMapper implements RowMapper<Expense> {

    private CategoryCache categoryCache = CategoryCache.getInstance();
    private BankCache bankCache = BankCache.getInstance();

    @Override
    public Expense mapRow(ResultSet rs, int i) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getLong("id"));
        expense.setAmount(rs.getFloat("amount"));
        expense.setNotes(rs.getString("notes"));
        expense.setCategory(categoryCache.getById(rs.getLong("category_id")));
        expense.setDate(rs.getDate("created_at"));

        Bank bank = bankCache.getById(rs.getLong("bank_id"));
        UserAccountInfo account = new UserAccountInfo();
        account.setBank(bank);
        account.setAccountNumber(rs.getString("account_number"));
        account.setAmount(rs.getFloat("account_amount"));
        account.setId(rs.getLong("account_id"));
        expense.setAccount(account);

        return expense;
    }
}
