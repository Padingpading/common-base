package com.padingpading.util.date;


import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 *
 * 获取 年、月、日、时、分、秒 值
 */
public class DateUtils {

    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    private DateUtils() {
    }

    public static Date now() {
        return new Date();
    }

    public static long millisecondTimestamp() {
        return System.currentTimeMillis();
    }

    public static long secondTimestamp() {
        return TimeUnit.MILLISECONDS.toSeconds(millisecondTimestamp());
    }

    /*获取 年、月、日、时、分、秒*/
    public static int getYear(Date date) {
        return getTimeField(date, 1);
    }

    public static int getMonth(Date date) {
        return getTimeField(date, 2);
    }

    public static int getDate(Date date) {
        return getTimeField(date, 5);
    }

    public static int getHour(Date date) {
        return getTimeField(date, 11);
    }

    public static int getMinute(Date date) {
        return getTimeField(date, 12);
    }

    public static int getSecond(Date date) {
        return getTimeField(date, 13);
    }

    public static int getMillisecond(Date date) {
        return getTimeField(date, 14);
    }

    private static int getTimeField(Date date, int calendarField) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(calendarField);
    }




    public static Date instance(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        calendar.set(14, millisecond);
        return calendar.getTime();
    }

    public static Date instance(int year, int month, int day, int hour, int minute, int second) {
        return instance(year, month, day, hour, minute, second, 0);
    }

    public static Date instance(int year, int month, int day) {
        return instance(year, month, day, 0, 0, 0, 0);
    }
}
