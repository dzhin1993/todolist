package com.zhinkoilya1993.todolist.config;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;


public class DateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String str, Locale locale) throws ParseException {
        return StringUtils.isEmpty(str) ? null : LocalDateTime.parse(str);
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {
        return dateTime.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
