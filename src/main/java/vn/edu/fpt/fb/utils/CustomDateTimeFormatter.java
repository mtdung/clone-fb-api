package vn.edu.fpt.fb.utils;

import java.time.format.DateTimeFormatter;

/**
 * vn.edu.fpt.accounts.utils
 **/

public class CustomDateTimeFormatter {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_HORO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
}
