package ru.tbcarus.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeUtil {


    private DateTimeUtil() {
    }



    public static LocalDateTime today() {
        return LocalDateTime.now().with(LocalTime.MIN);
    }
}
