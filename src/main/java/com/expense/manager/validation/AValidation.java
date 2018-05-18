package com.expense.manager.validation;

public abstract class AValidation {

    private AValidation next;

    public AValidation(AValidation next){
        this.next = next;
    }

    public boolean validate() {
        boolean valid = true;
        while(valid && next != null){
            valid = this.next.isValid();
        }
        return valid;
    }

    protected abstract boolean isValid();

}
