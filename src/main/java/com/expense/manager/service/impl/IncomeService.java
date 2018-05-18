package com.expense.manager.service.impl;

import com.expense.manager.bo.Income;
import com.expense.manager.dao.IIncomeDao;
import com.expense.manager.exception.ValidationException;
import com.expense.manager.service.IIncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IncomeService implements IIncomeService {
    private static final Logger logger = LoggerFactory.getLogger(IncomeService.class);

    @Autowired
    private IIncomeDao incomeDao;
    @Autowired
    private IValidationService validationService;

    @Override
    public long save(Income income, long userId) throws ValidationException {
        if(!validationService.validate(income)) {
            throw new ValidationException("income record is invalid " + income);
        }
        logger.info("saving income {}", income);
        return incomeDao.save(income, userId);
    }

    @Override
    public void update(Income income, long userId) throws ValidationException {
        if(!validationService.validate(income)) {
            throw new ValidationException("income record is invalid " + income);
        }
        logger.info("updating income {}", income);
        incomeDao.update(income, userId);
    }

    @Override
    public Income get(long id, long userId) throws ValidationException {
        if(!validationService.validateId(id)) {
            throw new ValidationException("Invalid id " + id);
        }
        logger.info("getting income record for {}", id);
        return incomeDao.get(id, userId);
    }

    @Override
    public void delete(long id, long userId) throws ValidationException {
        if(!validationService.validateId(id)) {
            throw new ValidationException("Invalid id " + id);
        }
        logger.info("deteling income record for {}", id);
        incomeDao.delete(id, userId);
    }

    @Override
    public List<Income> getByDate(Date start, Date end, long userId) throws ValidationException {
        if(!validationService.validateDateRange(start, end)) {
            throw new ValidationException("Invalid date range {" + start +"," + end + "}");
        }
        logger.info("getting income records for date range {} to {}", start, end);
        return incomeDao.getByDate(new java.sql.Date(start.getTime()), new java.sql.Date(end.getTime()), userId);
    }

}
