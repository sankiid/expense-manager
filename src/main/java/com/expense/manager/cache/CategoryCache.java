package com.expense.manager.cache;

import com.expense.manager.bo.Category;
import com.expense.manager.enums.Type;
import com.expense.manager.exception.CategoryNotFoundException;
import com.expense.manager.service.ICategoryService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CategoryCache {

    private Map<String, Category> nameToIdCache = new HashMap<>();
    private Map<Long, Category> idToNameCache = new HashMap<>();
    private ICategoryService categoryService = null;
    private static CategoryCache categoryCache = new CategoryCache();

    private CategoryCache(){
    }

    public static CategoryCache getInstance(){
        return categoryCache;
    }

    public void loadAll() {
        List<Category> categories = categoryService.getAllIncomeCategory();
        categories.forEach(c -> {
            setValues(c);
        });
    }

    public void setValues(Category c) {
        nameToIdCache.put(c.getName(), c);
        idToNameCache.put(c.getId(), c);
    }

    public Category getByName(String name){
        Category category = nameToIdCache.get(name);
        if(category == null){
            Category cat = categoryService.getCategoryByName(name);
            if(cat != null){
                setValues(cat);
                category = nameToIdCache.get(name);
            }
        }
        return category;
    }

    public Category getById(long id){
        Category category = idToNameCache.get(id);
        if(category == null){
            Category cat = categoryService.getCategoryById(id);
            if(cat != null) {
                setValues(cat);
                category = idToNameCache.get(id);
            }
        }
        return category;
    }

    public void init(ICategoryService categoryService) {
        if(this.categoryService == null){
            this.categoryService = categoryService;
        }
    }

    public List<Category> getByTypes(String type) throws CategoryNotFoundException {
        Type type1 = Type.value(type);
        if(type1 == null){
            throw new CategoryNotFoundException("Unable to find category for given type");
        }
        List<Category> list = new LinkedList<>();
        nameToIdCache.values().forEach(c -> {
            if(c.getType() == type1){
                list.add(c);
            }
        });
        return list;
    }
}
