package com.expense.manager.mapper;

import com.expense.manager.bo.UserAccountInfo;
import com.expense.manager.cache.BankCache;
import com.expense.manager.cache.CategoryCache;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInfoMapper implements RowMapper<UserAccountInfo> {

    private CategoryCache categoryCache = CategoryCache.getInstance();
    private BankCache bankCache = BankCache.getInstance();

    @Override
    public UserAccountInfo mapRow(ResultSet rs, int i) throws SQLException {

        UserAccountInfo uai = new UserAccountInfo();
        uai.setId(rs.getLong("id"));
        uai.setUserId(rs.getInt("user_id"));
        uai.setBank(bankCache.getById(rs.getInt("bank_id")));
        uai.setCreatedAt(rs.getDate("created_at"));
        uai.setUpdatedAt(rs.getDate("updated_at"));
        uai.setAmount(rs.getFloat("amount"));
        uai.setAccountNumber(rs.getString("account_number"));
        return uai;
    }
}