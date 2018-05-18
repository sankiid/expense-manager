package com.expense.manager.dao;

import com.expense.manager.bo.Income;

import java.sql.Date;
import java.util.List;

public interface IIncomeDao {
    long save(Income income, long userId);

    void update(Income income, long userId);

    void delete(long id, long userId);

    Income get(long id, long userId);

    List<Income> getByDate(Date start, Date end, long userId);
}
