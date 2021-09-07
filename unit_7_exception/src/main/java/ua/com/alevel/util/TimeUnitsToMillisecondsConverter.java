package ua.com.alevel.util;

import static ua.com.alevel.util.ClassConstants.*;

public class TimeUnitsToMillisecondsConverter {

    public static long convertsSecondsToMilliseconds(long userSpecifiedSeconds) {
        return MILLISECONDS_IN_ONE_SECOND * userSpecifiedSeconds;
    }

    public static long convertsMinutesToMilliseconds(long userSpecifiedMinutes) {
        return MILLISECONDS_IN_ONE_MINUTE * userSpecifiedMinutes;
    }

    public static long convertsHoursToMilliseconds(long userSpecifiedHours) {
        return MILLISECONDS_IN_ONE_HOURS * userSpecifiedHours;
    }

    public static long convertsDaysToMilliseconds(long userSpecifiedDays) {
        return MILLISECONDS_IN_ONE_DAY * userSpecifiedDays;
    }

    public static long convertsCommonAstronomicalYearsToMilliseconds(int commonAstronomicalYears) {
        return MILLISECONDS_IN_COMMON_ASTRONOMICAL_YEAR * commonAstronomicalYears;
    }

    public static long convertsLeapYearsToMilliseconds(int leapYears) {
        return MILLISECONDS_IN_LEAP_YEAR * leapYears;
    }

    public static double convertsMillisecondsToSeconds(long milliseconds) {
        return (double) milliseconds / MILLISECONDS_IN_ONE_SECOND;
    }

    public static double convertsMillisecondsToMinutes(long milliseconds) {
        return (double) milliseconds / MILLISECONDS_IN_ONE_MINUTE;
    }

    public static double convertsMillisecondsToHours(long milliseconds) {
        return (double) milliseconds / MILLISECONDS_IN_ONE_HOURS;
    }

    public static double convertsMillisecondsToDays(long milliseconds) {
        return (double) milliseconds / MILLISECONDS_IN_ONE_DAY;
    }
}
