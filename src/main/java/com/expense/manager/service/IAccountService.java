package com.expense.manager.service;

import com.expense.manager.bo.UserAccountInfo;

import java.util.List;

public interface IAccountService {
    long save(UserAccountInfo accountInfo, long userId);

    void update(UserAccountInfo accountInfo, long userId);

    UserAccountInfo get(long id, long userId);

    List<UserAccountInfo> getAll(long userId);

    void delete(long id, long userId);
}
