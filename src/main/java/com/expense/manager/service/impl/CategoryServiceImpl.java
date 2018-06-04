package com.expense.manager.service.impl;

import com.expense.manager.bo.Category;
import com.expense.manager.cache.CategoryCache;
import com.expense.manager.dao.ICategoryDao;
import com.expense.manager.exception.CategoryNotFoundException;
import com.expense.manager.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;
    private CategoryCache categoryCache = CategoryCache.getInstance();

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

    @Override
    public long save(Category category) {
        long catId = categoryDao.save(category);
        category.setId(catId);
        categoryCache.setValues(category);
        return catId;
    }

    @Override
    public List<Category> getCategoryByType(String type) throws CategoryNotFoundException {
        return categoryCache.getByTypes(type);
    }
}
