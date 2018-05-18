package com.expense.manager.service.impl;

import com.expense.manager.bo.Expense;
import com.expense.manager.bo.Income;

import java.util.Date;

public interface IValidationService {

    public boolean validate(Income income);

    public boolean validate(Expense expense);

    public boolean validateId(Long id);

    public boolean validateDateRange(Date start, Date end);

}
