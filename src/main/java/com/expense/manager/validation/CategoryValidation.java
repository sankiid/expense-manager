package com.expense.manager.validation;

import com.expense.manager.bo.Category;
import com.expense.manager.cache.CategoryCache;
import com.expense.manager.enums.Type;

public class CategoryValidation extends AValidation {

    private final long catId;
    private final Type type;

    private CategoryCache categoryCache = CategoryCache.getInstance();
    public CategoryValidation(AValidation next, long catId, Type type) {
        super(next);
        this.catId = catId;
        this.type = type;
    }

    @Override
    protected boolean isValid() {
        Category cat = categoryCache.getById(catId);
        if(cat.getType() == type){
            return true;
        }else if(cat.getType() == Type.INCOME_AND_EXPENSE && type == Type.INCOME){
            return true;
        }else if(cat.getType() == Type.INCOME_AND_EXPENSE && type == Type.EXPENSE){
            return true;
        }else{
            return false;
        }
    }
}
