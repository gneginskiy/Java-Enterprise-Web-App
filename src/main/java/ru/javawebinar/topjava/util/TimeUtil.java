package ru.javawebinar.topjava.util;

import java.time.LocalTime;

/**
 * Created by Greg Neginskiy on 02.09.2016
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}