package com.expense.manager.mapper;

import com.expense.manager.bo.Bank;
import com.expense.manager.bo.Income;
import com.expense.manager.bo.UserAccountInfo;
import com.expense.manager.cache.BankCache;
import com.expense.manager.cache.CategoryCache;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IncomeMapper implements RowMapper<Income> {

    private CategoryCache categoryCache = CategoryCache.getInstance();
    private BankCache bankCache = BankCache.getInstance();

    @Override
    public Income mapRow(ResultSet rs, int i) throws SQLException {
        Income income = new Income();
        income.setId(rs.getLong("id"));
        income.setAmount(rs.getFloat("amount"));
        income.setNotes(rs.getString("notes"));
        income.setCategory(categoryCache.getById(rs.getLong("category_id")));
        income.setDate(rs.getDate("created_at"));

        Bank bank = bankCache.getById(rs.getLong("bank_id"));
        UserAccountInfo account = new UserAccountInfo();
        account.setBank(bank);
        account.setAccountNumber(rs.getString("account_number"));
        account.setAmount(rs.getFloat("account_amount"));
        account.setId(rs.getLong("account_id"));

        income.setAccount(account);
        return income;
    }
}
