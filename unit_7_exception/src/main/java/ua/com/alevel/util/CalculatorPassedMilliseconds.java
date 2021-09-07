package ua.com.alevel.util;

import ua.com.alevel.dataclass.Date;

import static ua.com.alevel.util.ClassConstants.*;
import static ua.com.alevel.util.TimeUnitsToMillisecondsConverter.*;

public class CalculatorPassedMilliseconds {

    public static long calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(Date userSpecifiedInputData) {
        long totalMillisecondsPastBeforeUserSpecifiedInput = STARTING_ZERO_POINT_FOR_COUNTING_THE_NUMBER_OF_MILLISECONDS;
        for (long dataMilliseconds : new long[]{calculatesTotalPassedMillisecondsUserSpecifiedYearSinceBeginningOfNewEra(userSpecifiedInputData.getYear()),
                calculatesTotalPassedMillisecondsUserSpecifiedMonth(userSpecifiedInputData.getMonth(), userSpecifiedInputData.getYear()),
                convertsDaysToMilliseconds(userSpecifiedInputData.getDay()), convertsHoursToMilliseconds(userSpecifiedInputData.getHours()),
                convertsMinutesToMilliseconds(userSpecifiedInputData.getMinutes()), convertsSecondsToMilliseconds(userSpecifiedInputData.getSeconds())}) {
            totalMillisecondsPastBeforeUserSpecifiedInput += dataMilliseconds;
        }
        totalMillisecondsPastBeforeUserSpecifiedInput += userSpecifiedInputData.getMilliseconds();
        return totalMillisecondsPastBeforeUserSpecifiedInput;
    }

    public static int calculatesOnlyLeapYearsPastBeforeUserSpecifiedYear(int userSpecifiedYear) {
        return userSpecifiedYear / HIGH_RISE_YEAR_EVERY_FOUR_YEARS - userSpecifiedYear / MULTIPLICITY_ONE_HUNDRED +
                userSpecifiedYear / MULTIPLICITY_FOUR_HUNDRED;
    }

    public static int calculatesOnlyAstronomicalYearsPastBeforeUserSpecifiedYear(int userSpecifiedYear) {
        return userSpecifiedYear - calculatesOnlyLeapYearsPastBeforeUserSpecifiedYear(userSpecifiedYear);
    }

    public static long calculatesLeapYearsPastBeforeUserSpecifiedYearToMilliseconds(int userSpecifiedYear) {
        return convertsLeapYearsToMilliseconds(calculatesOnlyLeapYearsPastBeforeUserSpecifiedYear(userSpecifiedYear));
    }

    public static long calculatesCommonAstronomicalYearPastBeforeUserSpecifiedYearToMilliseconds(int userSpecifiedYear) {
        return convertsCommonAstronomicalYearsToMilliseconds(calculatesOnlyAstronomicalYearsPastBeforeUserSpecifiedYear(userSpecifiedYear));
    }

    public static long calculatesTotalPassedMillisecondsUserSpecifiedYearSinceBeginningOfNewEra(int userSpecifiedYear) {
        return calculatesCommonAstronomicalYearPastBeforeUserSpecifiedYearToMilliseconds(userSpecifiedYear) +
                calculatesLeapYearsPastBeforeUserSpecifiedYearToMilliseconds(userSpecifiedYear);
    }

    public static long calculatesTotalPassedMillisecondsUserSpecifiedMonth(int userSpecifiedMonth, int userSpecifiedYear) {
        long totalMillisecondsPerMonth = STARTING_ZERO_POINT_FOR_COUNTING_THE_NUMBER_OF_MILLISECONDS;
        userSpecifiedMonth--;
        switch (userSpecifiedMonth) {
            case NUMBER_OF_THE_MONTH_JANUARY_IN_THE_CALENDAR:
            case NUMBER_OF_THE_MONTH_MARCH_IN_THE_CALENDAR:
            case NUMBER_OF_THE_MONTH_MAY_IN_THE_CALENDAR:
            case NUMBER_OF_THE_MONTH_JULY_IN_THE_CALENDAR:
            case NUMBER_OF_THE_MONTH_AUGUST_IN_THE_CALENDAR:
            case NUMBER_OF_THE_MONTH_OCTOBER_IN_THE_CALENDAR:
            case NUMBER_OF_THE_MONTH_DECEMBER_IN_THE_CALENDAR:
                totalMillisecondsPerMonth += convertsDaysToMilliseconds(COMMON_DAYS_IN_FULL_MONTH);
                totalMillisecondsPerMonth += calculatesTotalPassedMillisecondsUserSpecifiedMonth(userSpecifiedMonth, userSpecifiedYear);
                break;
            case NUMBER_OF_THE_MONTH_FEBRUARY_IN_THE_CALENDAR:
                if (isLeapYear(userSpecifiedYear))
                    totalMillisecondsPerMonth += convertsDaysToMilliseconds(COMMON_DAYS_IN_FEBRUARY_LEAP_YEAR);
                else
                    totalMillisecondsPerMonth += convertsDaysToMilliseconds(COMMON_DAYS_IN_FEBRUARY_ASTRONOMICAL_YEAR);
                totalMillisecondsPerMonth += calculatesTotalPassedMillisecondsUserSpecifiedMonth(userSpecifiedMonth, userSpecifiedYear);
                break;
            case INPUT_ZERO_POINT:
                break;
            default:
                totalMillisecondsPerMonth += convertsDaysToMilliseconds(COMMON_DAYS_IN_SHORT_MONTH);
                totalMillisecondsPerMonth += calculatesTotalPassedMillisecondsUserSpecifiedMonth(userSpecifiedMonth, userSpecifiedYear);
        }
        return totalMillisecondsPerMonth;
    }

    public static boolean isLeapYear(int userSpecifiedYear) {
        return userSpecifiedYear % HIGH_RISE_YEAR_EVERY_FOUR_YEARS == REMAINDER_OF_THE_DIVISION &&
                userSpecifiedYear % MULTIPLICITY_ONE_HUNDRED != REMAINDER_OF_THE_DIVISION ||
                userSpecifiedYear % MULTIPLICITY_FOUR_HUNDRED == REMAINDER_OF_THE_DIVISION;
    }
}
