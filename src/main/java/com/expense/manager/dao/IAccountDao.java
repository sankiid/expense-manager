package com.expense.manager.dao;

import com.expense.manager.bo.UserAccountInfo;

import java.util.List;

public interface IAccountDao {

    long save(UserAccountInfo accountInfo, long userId);

    void update(UserAccountInfo accountInfo, long userId);

    UserAccountInfo get(long id, long userId);

    List<UserAccountInfo> get(long userId);

    void delete(long id, long userId);

    void updateAmount(float amount, long id, long userId);

}
