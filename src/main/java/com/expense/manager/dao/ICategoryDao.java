package com.expense.manager.dao;

import com.expense.manager.bo.Category;

import java.util.List;

public interface ICategoryDao {
    List<Category> getAllIncomeCategory();

    Category getCategoryByName(String name);

    Category getCategoryById(long id);
}
