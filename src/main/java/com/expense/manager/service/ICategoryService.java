package com.expense.manager.service;

import com.expense.manager.bo.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllIncomeCategory();

    Category getCategoryByName(String name);

    Category getCategoryById(long id);
}
