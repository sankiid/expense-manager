package com.expense.manager.service.impl;

import com.expense.manager.bo.Expense;
import com.expense.manager.bo.Income;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ValidationServiceImpl implements IValidationService {



    @Override
    public boolean validate(Income income) {
        return true;
    }

    @Override
    public boolean validate(Expense expense) {
        return true;
    }

    @Override
    public boolean validateId(Long id) {
        return true;
    }

    @Override
    public boolean validateDateRange(Date start, Date end) {
        return true;
    }
}
