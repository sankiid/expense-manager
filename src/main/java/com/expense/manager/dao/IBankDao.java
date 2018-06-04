package com.expense.manager.dao;

import com.expense.manager.bo.Bank;

import java.util.List;

public interface IBankDao {
    List<Bank> getAllBanks();

    Bank getBankById(long id);

    long save(Bank bank);
}
