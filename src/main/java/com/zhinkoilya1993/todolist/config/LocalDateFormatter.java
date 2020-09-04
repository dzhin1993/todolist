package com.zhinkoilya1993.todolist.config;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String str, Locale locale) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
