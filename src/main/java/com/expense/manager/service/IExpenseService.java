package com.expense.manager.service;

import com.expense.manager.bo.Expense;
import com.expense.manager.bo.Income;
import com.expense.manager.exception.ValidationException;

import java.util.Date;
import java.util.List;

public interface IExpenseService {
    long save(Expense expense, long userId) throws ValidationException;

    void update(Expense expense, long userId) throws ValidationException;

    Expense get(long id, long userId) throws ValidationException;

    void delete(long id, long userId) throws ValidationException;

    List<Expense> getByDate(Date start, Date end, long userId) throws ValidationException;
}
