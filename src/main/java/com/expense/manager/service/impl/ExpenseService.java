package com.expense.manager.service.impl;

import com.expense.manager.bo.Expense;
import com.expense.manager.dao.IExpenseDao;
import com.expense.manager.exception.ValidationException;
import com.expense.manager.service.IExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseService implements IExpenseService {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private IExpenseDao expenseDao;
    @Autowired
    private IValidationService validationService;

    @Override
    public long save(Expense expense, long userId) throws ValidationException {
        if(!validationService.validate(expense)) {
            throw new ValidationException("expense record is invalid " + expense);
        }
        logger.info("saving expense {}", expense);
        return expenseDao.save(expense, userId);
    }

    @Override
    public void update(Expense expense, long userId) throws ValidationException {
        if(!validationService.validate(expense)) {
            throw new ValidationException("expense record is invalid " + expense);
        }
        logger.info("updating expense {}", expense);
        expenseDao.update(expense, userId);
    }

    @Override
    public Expense get(long id, long userId) throws ValidationException {
        if(!validationService.validateId(id)) {
            throw new ValidationException("Invalid id " + id);
        }
        logger.info("getting expense record for {}", id);
        return expenseDao.get(id, userId);
    }

    @Override
    public void delete(long id, long userId) throws ValidationException {
        if(!validationService.validateId(id)) {
            throw new ValidationException("Invalid id " + id);
        }
        logger.info("deteling expense record for {}", id);
        expenseDao.delete(id, userId);
    }

    @Override
    public List<Expense> getByDate(Date start, Date end, long userId) throws ValidationException {
        if(!validationService.validateDateRange(start, end)) {
            throw new ValidationException("Invalid date range {" + start +"," + end + "}");
        }
        logger.info("getting expense records for date range {} to {}", start, end);
        return expenseDao.getByDate(new java.sql.Date(start.getTime()), new java.sql.Date(end.getTime()), userId);
    }

}
