package com.expense.manager.service;

import com.expense.manager.bo.Category;
import com.expense.manager.exception.CategoryNotFoundException;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllIncomeCategory();

    Category getCategoryByName(String name);

    Category getCategoryById(long id);

    long save(Category category);

    List<Category> getCategoryByType(String type) throws CategoryNotFoundException;
}
