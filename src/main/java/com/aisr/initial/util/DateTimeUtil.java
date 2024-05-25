package com.aisr.initial.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String formatCurrentDate(DateTimeFormatter formatter) {
        try {
            return LocalDateTime.now().format(formatter);
        } catch (DateTimeException e) {
            System.err.println("Could not format date : "+ e.getMessage());
        }
        return null;
    }
}
