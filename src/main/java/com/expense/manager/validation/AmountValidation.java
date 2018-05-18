package com.expense.manager.validation;

public class AmountValidation extends AValidation {

    private double data;

    public AmountValidation(AValidation next, double amount) {
        super(next);
        this.data = data;
    }

    @Override
    protected boolean isValid() {
        if(data > 0)
            return true;
        return false;
    }
}
