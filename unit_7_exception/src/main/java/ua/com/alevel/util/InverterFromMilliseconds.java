package ua.com.alevel.util;

import ua.com.alevel.dataclass.Date;

import static ua.com.alevel.util.CalculatorPassedMilliseconds.isLeapYear;
import static ua.com.alevel.util.ClassConstants.*;
import static ua.com.alevel.util.TimeUnitsToMillisecondsConverter.convertsMillisecondsToDays;

public class InverterFromMilliseconds {

    public static double transfersMillisecondsToYears(long milliseconds) {
        double numberOfPastYears = ZERO_REFERENCE_POINT_OF_PAST_YEARS;
        int countingOfThePastYears = FIRST_PAST_YEAR;
        while (true) {
            if (milliseconds == ZERO_NUMBER_OF_MILLISECONDS)
                return numberOfPastYears;
            if (isLeapYear(countingOfThePastYears)) {
                if (milliseconds >= MILLISECONDS_IN_LEAP_YEAR) {
                    milliseconds -= MILLISECONDS_IN_LEAP_YEAR;
                    numberOfPastYears++;
                } else {
                    numberOfPastYears += convertsMillisecondsToDays(milliseconds) / COMMON_DAYS_IN_ASTRONOMICAL_YEAR;
                    milliseconds = ZERO_NUMBER_OF_MILLISECONDS;
                }
            } else {
                if (milliseconds >= MILLISECONDS_IN_COMMON_ASTRONOMICAL_YEAR) {
                    milliseconds -= MILLISECONDS_IN_COMMON_ASTRONOMICAL_YEAR;
                    numberOfPastYears++;
                } else {
                    numberOfPastYears += convertsMillisecondsToDays(milliseconds) / COMMON_DAYS_IN_LEAP_YEAR;
                    milliseconds = ZERO_NUMBER_OF_MILLISECONDS;
                }
            }
            countingOfThePastYears++;
        }
    }

    public static Date createsFullDate(long milliseconds) {
        Date dateClass = new Date();
        int numberOfPastMonth = ZERO_MONTH_SINCE_THE_BEGINNING_OF_THE_YEAR;
        int numberOfPastYears = ZERO_YEAR_SINCE_THE_BEGINNING_OF_THE_ERA;
        int calendarCurrentMonthNumber = INDEX_ARRAY_FIRST_CALENDAR_MONTH;

        long numberOfPastDays = countsDays(milliseconds);
        while (numberOfPastDays > MONTH_DAYS[calendarCurrentMonthNumber]) {
            numberOfPastDays -= MONTH_DAYS[calendarCurrentMonthNumber];
            if (calendarCurrentMonthNumber == ONE_DAY) {
                if (isLeapYear(numberOfPastYears)) {
                    numberOfPastDays -= ONE_DAY;
                }
            }
            calendarCurrentMonthNumber += ONE_MONTH;

            if (calendarCurrentMonthNumber > YEAR_WITHOUT_ONE_MONTH) {
                numberOfPastYears += ONE_YEAR;
                calendarCurrentMonthNumber = INDEX_ARRAY_FIRST_CALENDAR_MONTH;
            }
            numberOfPastMonth += ONE_MONTH;
        }

        dateClass.setMilliseconds((int) (milliseconds % MILLISECONDS_IN_ONE_SECOND));
        dateClass.setSeconds((int) (countsSeconds(milliseconds) % SECONDS_IN_ONE_MINUTES));
        dateClass.setMinutes((int) (countsMinutes(milliseconds) % MINUTES_IN_ONE_HOURS));
        dateClass.setHours((int) (countsHours(milliseconds) % HOURS_IN_ONE_DAY));
        dateClass.setDay((int) ((numberOfPastDays) % MONTH_DAYS[calendarCurrentMonthNumber]));
        if (isLeapYear(numberOfPastYears)) {
            dateClass.setDay((int) ((numberOfPastDays) % MONTH_DAYS[calendarCurrentMonthNumber]));
        } else {
            dateClass.setDay((int) ((numberOfPastDays) % MONTH_DAYS[calendarCurrentMonthNumber]) + ONE_MONTH);
        }
        dateClass.setMonth(((numberOfPastMonth) % NUMBER_OF_MONTH_IN_THE_YEAR) + ONE_YEAR);
        dateClass.setYear(numberOfPastYears);
        return dateClass;
    }

    public static long countsSeconds(long milliseconds) {
        return (milliseconds / MILLISECONDS_IN_ONE_SECOND);
    }

    public static long countsMinutes(long milliseconds) {
        return countsSeconds(milliseconds) / SECONDS_IN_ONE_MINUTES;
    }

    public static long countsHours(long milliseconds) {
        return countsMinutes(milliseconds) / MINUTES_IN_ONE_HOURS;
    }

    public static long countsDays(long milliseconds) {
        return countsHours(milliseconds) / HOURS_IN_ONE_DAY;
    }
}
