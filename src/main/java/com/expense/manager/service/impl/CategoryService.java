package com.expense.manager.service.impl;

import com.expense.manager.bo.Category;
import com.expense.manager.dao.ICategoryDao;
import com.expense.manager.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    public List<Category> getAllIncomeCategory() {
        return categoryDao.getAllIncomeCategory();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDao.getCategoryByName(name);
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryDao.getCategoryById(id);
    }
}
