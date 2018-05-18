package com.expense.manager.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    private final SimpleDateFormat sdf;

    public DateConverter(String dateFormat) {
        this.sdf = new SimpleDateFormat(dateFormat);
    }

    @Override
    public Date convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
        }
        return null;
    }
}
