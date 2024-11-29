package com.library.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static LocalDate parseYYMMDD(String date){
        return LocalDate.parse(date,YYYYMMDD_FORMATTER);
    }

    public static LocalDateTime parseOffsetDateTime(String datetime){
        return LocalDateTime.parse(datetime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
