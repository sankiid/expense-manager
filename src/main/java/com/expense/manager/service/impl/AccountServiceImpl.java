package com.expense.manager.service.impl;

import com.expense.manager.bo.UserAccountInfo;
import com.expense.manager.dao.IAccountDao;
import com.expense.manager.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    @Override
    public long save(UserAccountInfo accountInfo, long userId) {
        return accountDao.save(accountInfo, userId);
    }

    @Override
    public void update(UserAccountInfo accountInfo, long userId) {
        accountDao.update(accountInfo, userId);
    }

    @Override
    public UserAccountInfo get(long id, long userId) {
        return accountDao.get(id, userId);
    }

    @Override
    public List<UserAccountInfo> getAll(long userId) {
        return accountDao.get(userId);
    }

    @Override
    public void delete(long id, long userId) {
        accountDao.delete(id, userId);
    }
}
