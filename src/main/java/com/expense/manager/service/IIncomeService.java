package com.expense.manager.service;

import com.expense.manager.bo.Income;
import com.expense.manager.exception.ValidationException;

import java.util.Date;
import java.util.List;

public interface IIncomeService {
    long save(Income income, long userId) throws ValidationException;

    void update(Income income, long userId) throws ValidationException;

    Income get(long id, long userId) throws ValidationException;

    void delete(long id, long userId) throws ValidationException;

    List<Income> getByDate(Date start, Date end, long userId) throws ValidationException;
}
