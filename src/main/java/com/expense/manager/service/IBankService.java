package com.expense.manager.service;

import com.expense.manager.bo.Bank;

import java.util.List;

public interface IBankService {
    List<Bank> getAllBanks();

    Bank getBankById(long id);

    void save(Bank bank);
}
