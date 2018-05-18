package com.expense.manager.validation;

import com.expense.manager.exception.ValidationException;

public class IdValidation extends AValidation {

    private final Long data;

    public IdValidation(AValidation next, Long data) {

        super(next);
        this.data = data;
    }

    @Override
    protected boolean isValid() {
        if(data.longValue() > 0)
            return true;
        return false;
    }
}
