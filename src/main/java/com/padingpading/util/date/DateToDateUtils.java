package com.padingpading.util.date;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 日期转日期
 * 添加/减少 年、月、周、时、分、秒
 * 月、天、时、分的上限
 * 月、天、时、分的下限
 */
public class DateToDateUtils {


    //月、天、时、分的下限

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


    //月、天、时、分的上限

    public static Date ceilMonth(Date date) {
        return ceil(date, 1);
    }

    public static Date ceilDay(Date date) {
        return ceil(date, 2);
    }

    public static Date ceilHour(Date date) {
        return ceil(date, 3);
    }

    public static Date ceilMinute(Date date) {
        return ceil(date, 4);
    }

    private static Date ceil(Date date, int scale) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(1);
        int month = calendar.get(2);
        int day = scale >= 2 ? calendar.get(5) : calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int hour = scale >= 3 ? calendar.get(11) : 23;
        int minute = scale == 4 ? calendar.get(12) : 59;
        calendar.set(year, month, day, hour, minute, 59);
        return calendar.getTime();
    }

    //添加/减少 年、月、周、时、分、秒

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
}
