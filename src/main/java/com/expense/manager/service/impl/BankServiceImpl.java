package com.expense.manager.service.impl;

import com.expense.manager.bo.Bank;
import com.expense.manager.cache.BankCache;
import com.expense.manager.dao.IBankDao;
import com.expense.manager.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements IBankService {

    @Autowired
    private IBankDao bankDao;

    @Override
    public List<Bank> getAllBanks() {
        return bankDao.getAllBanks();
    }

    @Override
    public Bank getBankById(long id) {
        return bankDao.getBankById(id);
    }

}
