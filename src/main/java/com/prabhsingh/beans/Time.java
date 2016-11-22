package com.prabhsingh.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by prabh on 2016-11-20.
 */
public class Time {
    public Time() {
    }
    public String getToday() {
        LocalDate timePoint = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return timePoint.format(formatter);
    }

    public String getPreviousDay() {
        LocalDate timePoint = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return timePoint.minusDays(1).format(formatter);
    }

    public String getLast30Days() {
        LocalDate timePoint = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return timePoint.minusDays(30).format(formatter);
    }
}
