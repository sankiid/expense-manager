package com.expense.manager.dao;

import com.expense.manager.bo.Expense;

import java.sql.Date;
import java.util.List;

public interface IExpenseDao {
    long save(Expense expense, long userId);

    void update(Expense expense, long userId);

    void delete(long id, long userId);

    Expense get(long id, long userId);

    List<Expense> getByDate(Date start, Date end, long userId);
}
