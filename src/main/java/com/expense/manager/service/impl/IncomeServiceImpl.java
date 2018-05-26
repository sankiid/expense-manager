package com.expense.manager.service.impl;

import com.expense.manager.bo.Income;
import com.expense.manager.dao.IAccountDao;
import com.expense.manager.dao.IIncomeDao;
import com.expense.manager.exception.ValidationException;
import com.expense.manager.service.IIncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class IncomeServiceImpl implements IIncomeService {
    private static final Logger logger = LoggerFactory.getLogger(IncomeServiceImpl.class);

    @Autowired
    private IIncomeDao incomeDao;
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private IValidationService validationService;

    @Override
    @Transactional
    public long save(Income income, long userId) throws ValidationException {
        if(!validationService.validate(income)) {
            throw new ValidationException("income record is invalid " + income);
        }
        logger.info("saving income {}", income);
        accountDao.updateAmount(income.getAmount(), income.getAccount().getId(), userId);
        return incomeDao.save(income, userId);
    }

    @Override
    @Transactional
    public void update(Income income, long userId) throws ValidationException {
        if(!validationService.validate(income)) {
            throw new ValidationException("income record is invalid " + income);
        }
        logger.info("updating income {}", income);

        Income oldIncome = get(income.getId(), userId);
        if(oldIncome.getAccount().getId() == income.getAccount().getId()){
            if(oldIncome.getAmount() != income.getAmount()){
                accountDao.updateAmount(income.getAmount() - oldIncome.getAmount(), income.getAccount().getId(), userId);
            }
        }else{
            accountDao.updateAmount(- oldIncome.getAmount(), oldIncome.getAccount().getId(), userId);
            accountDao.updateAmount(income.getAmount(), income.getAccount().getId(), userId);
        }

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
    @Transactional
    public void delete(long id, long userId) throws ValidationException {
        if(!validationService.validateId(id)) {
            throw new ValidationException("Invalid id " + id);
        }
        logger.info("deteling income record for {}", id);
        Income income = get(id, userId);
        accountDao.updateAmount(-1*income.getAmount(), income.getAccount().getId(), userId);
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
