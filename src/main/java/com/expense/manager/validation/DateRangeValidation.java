package com.expense.manager.validation;


import java.util.Date;

public class DateRangeValidation extends AValidation {

    private final Date start;
    private final Date end;

    public DateRangeValidation(AValidation next,Date start, Date end) {
        super(next);
        this.start = start;
        this.end = end;
    }

    @Override
    protected boolean isValid() {
        if(start.getTime() < end.getTime())
            return true;
        return false;
    }
}
