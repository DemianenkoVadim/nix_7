package ua.com.alevel.dataconverter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ua.com.alevel.util.Constants.*;

public class DataConverter {

    public static String[] checkDateToString(List<String> dates) {
        List<String> datesWithoutAnyDelimiters = new ArrayList<>();
        for (String date : dates) {
            for (String dateFormat : formatsListOfDates()) {
                Matcher matcher = Pattern.compile(dateFormat).matcher(date);
                if (matcher.matches()) {
                    datesWithoutAnyDelimiters.add(compare(matcher.group(YEAR), matcher.group(MONTH), matcher.group(DAY)));
                    break;
                }
            }
        }
        return datesWithoutAnyDelimiters.toArray(new String[FIRST_DATA]);
    }

    public static List<String> formatsListOfDates() {
        return List.of(FORMAT_DATE_MONTH_DAY_YEAR, FORMAT_DATE_YEAR_MONTH_DAY, FORMAT_DATE_DAY_MONTH_YEAR);
    }

    private static String compare(String year, String month, String day) {
        boolean whatMonth = false;
        if (parsesAndChecksInputData(year, month, day)) {
            for (String calendarMonthNumber : NUMBERS_FULL_MONTH) {
                if (month.equals(calendarMonthNumber)) {
                    if (parsesAndChecksIfFullMonth(day))
                        whatMonth = true;
                }
            }
            for (String calendarMonthNumber : NUMBERS_SHORT_MONTH) {
                if (month.equals(calendarMonthNumber)) {
                    if (parsesAndChecksIfShortMonth(day))
                        whatMonth = true;
                }
            }
            if (month.equals(NUMBER_OF_THE_MONTH_FEBRUARY_IN_THE_CALENDAR)) {
                if (checksAndParsesIfLeapYear(year, day))
                    whatMonth = true;
                if (parsesCommonDaysInFebruaryAstronomicalYear(day))
                    whatMonth = true;
            }
            if (whatMonth)
                return configsData(year, month, day);
        }
        return EMPTY_STRING;
    }

    private static boolean parsesAndChecksInputData(String inputYear, String inputMonth, String inputDay) {
        return Integer.parseInt(inputYear) >= START_POINT_YEAR
                && Integer.parseInt(inputMonth) > START_POINT_MONTH
                && Integer.parseInt(inputMonth) <= COMMON_NUMBER_OF_MONTH_IN_YEAR
                && Integer.parseInt(inputDay) > START_POINT_DAY
                && Integer.parseInt(inputDay) <= COMMON_DAYS_IN_FULL_MONTH;
    }

    private static boolean parsesAndChecksIfShortMonth(String inputDay) {
        return Integer.parseInt(inputDay) <= COMMON_DAYS_IN_SHORT_MONTH;
    }

    private static boolean parsesAndChecksIfFullMonth(String inputDay) {
        return Integer.parseInt(inputDay) <= COMMON_DAYS_IN_FULL_MONTH;
    }

    private static boolean checksAndParsesIfLeapYear(String inputYear, String inputDay) {
        return (Integer.parseInt(inputDay) == COMMON_DAYS_IN_FEBRUARY_LEAP_YEAR
                && (Integer.parseInt(inputYear) % HIGH_RISE_YEAR_EVERY_FOUR_YEARS == REMAINDER_OF_THE_DIVISION
                && Integer.parseInt(inputYear) % MULTIPLICITY_ONE_HUNDRED != REMAINDER_OF_THE_DIVISION
                || Integer.parseInt(inputYear) % MULTIPLICITY_FOUR_HUNDRED == REMAINDER_OF_THE_DIVISION));
    }

    private static boolean parsesCommonDaysInFebruaryAstronomicalYear(String inputDay) {
        return Integer.parseInt(inputDay) <= COMMON_DAYS_IN_FEBRUARY_ASTRONOMICAL_YEAR;
    }

    private static String configsData(String inputYear, String inputMonth, String inputDay) {
        return inputYear + inputMonth + inputDay;
    }
}
