package com.padingpading.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author libin
 * @description 时间工具类
 * date-toString
 */
public class DateToStrUtils {

    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";

    private DateToStrUtils() {
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

    public static Date parse(String dateString, String pattern) throws ParseException {
        return (new SimpleDateFormat(pattern)).parse(dateString);
    }

    public static Date parse(String dateString) throws ParseException {
        return parse(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parse(Long time) {
        return new Date(time);
    }

    public static String format(Date date, String pattern) {
        return (new SimpleDateFormat(pattern)).format(date);
    }

    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatToDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String formatToTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }

    public static Date add(Date date, int amount, TimeUnit timeUnit) {
        switch(timeUnit) {
            case DAYS:
                return addDays(date, amount);
            case HOURS:
                return addHours(date, amount);
            case MINUTES:
                return addMinutes(date, amount);
            case SECONDS:
                return addSeconds(date, amount);
            case MILLISECONDS:
                return addMilliseconds(date, amount);
            default:
                throw new IllegalArgumentException("不支持小于毫秒的单位");
        }
    }

    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new NullPointerException();
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static int[] getTimeFields(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int[] timeFields = new int[]{calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13), calendar.get(14)};
        return timeFields;
    }

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

    public static Date floorMonth(Date date) {
        return floor(date, 1);
    }

    public static Date floorDay(Date date) {
        return floor(date, 2);
    }

    public static Date floorHour(Date date) {
        return floor(date, 3);
    }

    public static Date floorMinute(Date date) {
        return floor(date, 4);
    }

    private static Date floor(Date date, int scale) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(1);
        int month = calendar.get(2);
        int day = scale >= 2 ? calendar.get(5) : 1;
        int hour = scale >= 3 ? calendar.get(11) : 0;
        int minute = scale == 4 ? calendar.get(12) : 0;
        calendar.set(year, month, day, hour, minute, 0);
        calendar.set(14, 0);
        return calendar.getTime();
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
