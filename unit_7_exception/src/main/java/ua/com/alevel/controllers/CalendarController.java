package ua.com.alevel.controllers;

import ua.com.alevel.dataclass.Date;
import ua.com.alevel.exception.DateException;
import ua.com.alevel.service.CheckFormat;
import ua.com.alevel.util.CalculatorDates;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static ua.com.alevel.controllers.SupportController.*;
import static ua.com.alevel.service.RequestsAndMessagesInformation.*;
import static ua.com.alevel.util.CalculatorPassedMilliseconds.isLeapYear;
import static ua.com.alevel.util.ClassConstants.*;

public class CalendarController {

    private static final Scanner userInput = new Scanner(System.in);
    private static final CheckFormat checksFormat = new CheckFormat();

    public void startsTheApplication() {
        printAvailableFormats();
        boolean quitTheApplication = false;
        while (!quitTheApplication) {
            printsApplicationCapabilities();
            printsRequestToDo();
            String userChoose = userInput.nextLine();
            switch (userChoose) {
                case USER_CHOOSE_FIRST_POINT:
                    findsTheDifferenceBetweenDates();
                    break;
                case USER_CHOOSE_SECOND_POINT:
                    printsAndAdditionsOrSubtractsSomeTimeToDate();
                    break;
                case USER_CHOOSE_THIRD_POINT:
                    listOfInputDates();
                    break;
                case USER_CHOOSE_FOURS_POINT:
                    printAvailableFormats();
                    break;
                case USER_CHOOSE_ZERO_POINT:
                    quitTheApplication = true;
                    break;
                default:
                    printsMessageIncorrectSymbolInput();
            }
        }
    }

    public void findsTheDifferenceBetweenDates() {
        long resultDifferenceBetweenDates = CalculatorDates.differenceBetweenDates(formatsFirstData(), formatSecondData());
        printsDifferenceBetweenDatesInChosenValues(resultDifferenceBetweenDates);
        System.out.println();
    }

    public Date formatsFirstData() {
        printsRequestToEnterFirstDate();
        return checksCorrectFormatInput(checksFormatInputDate());
    }

    public Date formatSecondData() {
        printsRequestToEnterSecondDate();
        return checksCorrectFormatInput(checksFormatInputDate());
    }

    public Date formatDataInput() {
        printsMessageToEnterTheDate();
        return checksCorrectFormatInput(checksFormatInputDate());
    }

    public Date checksCorrectFormatInput(String userInputData) {
        Date data = null;
        try {
            data = checksFormat.convertsStringLineInToDate(userInputData);
        } catch (DateException wrongDateFormat) {
            wrongDateFormat.printStackTrace();
        }
        return data;
    }

    public static String checksFormatInputDate() {
        boolean newData = true;
        String data;
        do {
            if (!newData) {
                printsMessageWrongInput();
                printsMessageToEnterTheDate();
            }
            data = userInput.nextLine();
            newData = false;
        }
        while (checksFormatOfDate(data) || checksFormattingOfTheDataTime(data));
        return data;
    }

    private void additionToDateAndPrintsTheResult() {
        printsRequestInformationToAdd();
        long millisecondsToAdd = measuresAndConvertsIntoMilliseconds();
        printsMessageToChooseFormatData();
        printsFormatDataCapabilities();
        String choose = userInput.nextLine();
        Date inMilliseconds = CalculatorDates.addMillisecondsToDate(formatDataInput(), millisecondsToAdd);
        System.out.println(RESULT + checksFormat.convertsDateToString(inMilliseconds, choose));
    }

    private void subtractToDateAndPrintsTheResult() {
        printsRequestInformationToSubtract();
        long millisecondsToSubtract = measuresAndConvertsIntoMilliseconds();
        printsMessageToChooseFormatData();
        printsFormatDataCapabilities();
        String choose = userInput.nextLine();
        Date inMilliseconds = CalculatorDates.subtractMillisecondsFromDate(formatDataInput(), millisecondsToSubtract);
        System.out.println(RESULT + checksFormat.convertsDateToString(inMilliseconds, choose));
    }

    private void printsAndAdditionsOrSubtractsSomeTimeToDate() {
        printsRequestToAddOrSubtract();
        String userChoose = userInput.nextLine();
        switch (userChoose) {
            case USER_CHOOSE_FIRST_POINT:
                additionToDateAndPrintsTheResult();
                break;
            case USER_CHOOSE_SECOND_POINT:
                subtractToDateAndPrintsTheResult();
                break;
            default:
                printsMessageIncorrectSymbolInput();
        }
        System.out.println();
    }

    public static void listOfInputDates() {
        ArrayList<Date> enteredDates = new ArrayList<>();
        boolean stopEndingTheDates = false;
        while (!stopEndingTheDates) {
            printsMessageRequestInformationToAddDateOrExitTheApplication();
            String indexToAddData = userInput.nextLine();
            switch (indexToAddData) {
                case USER_CHOOSE_ZERO_POINT:
                    stopEndingTheDates = true;
                    break;
                case USER_CHOOSE_FIRST_POINT:
                    printsMessageToEnterTheDate();
                    String date;
                    boolean firstData = true;
                    do {
                        if (!firstData) {
                            printsMessageWrongInput();
                            printsMessageToEnterTheDate();
                        }
                        date = userInput.nextLine();
                        firstData = false;
                    } while (checksFormatOfDate(date) || checksFormattingOfTheDataTime(date));
                    Date myDate = null;
                    try {
                        myDate = checksFormat.convertsStringLineInToDate(date);
                    } catch (DateException wrongDateFormat) {
                        wrongDateFormat.printStackTrace();
                    }
                    enteredDates.add(myDate);
                    break;
                default:
                    printsMessageIncorrectSymbolInput();
            }
        }
        String sorting;
        printsMessageRequestInformationToSortDates();
        sorting = userInput.nextLine();
        sortsInputDates(enteredDates, sorting);
        printsMessageToChooseFormatData();
        printsFormatDataCapabilities();
        String choice = userInput.nextLine();
        System.out.println(RESULT_OF_SORT);
        for (Date calendarDates : enteredDates) {
            System.out.println(checksFormat.convertsDateToString(calendarDates, choice));
        }
        System.out.println();
    }

    public static String[] definesAnArrayDivisor(String separator, String userInput) {
        String[] splitters = new String[NUMBER_OF_DELIMITERS_IN_DATA];
        switch (separator) {
            case SEPARATOR_IN_THE_ENTERED_DATE_SLASH:
                userInput += FORMAT_DATA_TIME_PARAMETERS;
                splitters = userInput.split("[/ ]");
                break;
            case SEPARATOR_IN_THE_ENTERED_DATE_DASH:
                userInput += FORMAT_DATA_TIME_PARAMETERS;
                splitters = userInput.split("[- ]");
                break;
        }
        return splitters;
    }

    private static boolean checksFormatOfDate(String userInput) {
        try {
            if (userInput.contains(SEPARATOR_IN_THE_ENTERED_DATE_SLASH) || userInput.contains(SEPARATOR_IN_THE_ENTERED_DATE_DASH)) {
                String separator;
                if (userInput.contains(SEPARATOR_IN_THE_ENTERED_DATE_SLASH))
                    separator = SEPARATOR_IN_THE_ENTERED_DATE_SLASH;
                else separator = SEPARATOR_IN_THE_ENTERED_DATE_DASH;

                if (definesAnArrayDivisor(separator, userInput).length >= THIRD_DELIMITER_IN_ARRAY) {

                    int day;
                    if (definesAnArrayDivisor(separator, userInput)[FIRST_DELIMITER_IN_ARRAY].equals(NO_DELIMITER)) {
                        day = ONE_DAY;
                    } else {
                        day = Integer.parseInt(definesAnArrayDivisor(separator, userInput)[FIRST_DELIMITER_IN_ARRAY]);
                    }

                    int month;
                    if (definesAnArrayDivisor(separator, userInput)[SECOND_DELIMITER_IN_ARRAY].equals(NO_DELIMITER)) {
                        definesAnArrayDivisor(separator, userInput)[SECOND_DELIMITER_IN_ARRAY] = FIRST_MONTH;
                    }
                    month = Integer.parseInt(definesAnArrayDivisor(separator, userInput)[SECOND_DELIMITER_IN_ARRAY]);

                    long year;
                    if (definesAnArrayDivisor(separator, userInput)[THIRD_DELIMITER_IN_ARRAY].equals(NO_DELIMITER)) {
                        definesAnArrayDivisor(separator, userInput)[THIRD_DELIMITER_IN_ARRAY] = START_POINT_YEAR;
                    }
                    year = Integer.parseInt(definesAnArrayDivisor(separator, userInput)[THIRD_DELIMITER_IN_ARRAY]);


                    if ((definesAnArrayDivisor(separator, userInput)[FIRST_DELIMITER_IN_ARRAY].matches("[-+]?\\d+") ||
                            definesAnArrayDivisor(separator, userInput)[FIRST_DELIMITER_IN_ARRAY].matches(NO_DELIMITER))
                            && (definesAnArrayDivisor(separator, userInput)[SECOND_DELIMITER_IN_ARRAY].matches("[-+]?\\d+"))
                            && definesAnArrayDivisor(separator, userInput)[THIRD_DELIMITER_IN_ARRAY].matches("[-+]?\\d+")) {
                        if (day > START_POINT_DAY && month <= COMMON_NUMBER_OF_MONTH_IN_YEAR && month >= ONE_MONTH && year >= FIRST_YEAR_IN_ERA) {
                            boolean whatMonth = false;
                            for (String numberOfMonth : NUMBERS_FULL_MONTH) {
                                if (definesAnArrayDivisor(separator, userInput)[SECOND_DELIMITER_IN_ARRAY].equals(numberOfMonth)) {
                                    if (day <= COMMON_DAYS_IN_FULL_MONTH)
                                        whatMonth = true;
                                }
                            }
                            for (String numberOfMonth : NUMBERS_SHORT_MONTH) {
                                if (definesAnArrayDivisor(separator, userInput)[SECOND_DELIMITER_IN_ARRAY].equals(numberOfMonth)) {
                                    if (day <= COMMON_DAYS_IN_SHORT_MONTH)
                                        whatMonth = true;
                                }
                            }
                            if (definesAnArrayDivisor(separator, userInput)[SECOND_DELIMITER_IN_ARRAY].equals(SECOND_TEN)) {
                                if (day == COMMON_DAYS_IN_FEBRUARY_LEAP_YEAR && (isLeapYear(Math.toIntExact(year))))
                                    whatMonth = true;
                                if (day <= COMMON_DAYS_IN_FEBRUARY_ASTRONOMICAL_YEAR)
                                    whatMonth = true;
                            }
                            return !whatMonth;
                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                boolean isMonth = false;
                int countSpace = NO_SPACES;

                String[] split = userInput.split("[ ]");
                for (char element : userInput.toCharArray()) {
                    if (element == EMPTY_SPACE) countSpace++;
                }
                if (countSpace == NO_SPACES) {
                    if (split[FIRST_DELIMITER_IN_ARRAY].matches("[-+]?\\d+") &&
                            Integer.parseInt(split[FIRST_DELIMITER_IN_ARRAY]) >= ZERO_PARAMETER) {
                        return false;
                    }
                }
                if (userInput.contains(REGEX_COLON) && countSpace == ONE_SPACE) {
                    if (split[FIRST_DELIMITER_IN_ARRAY].matches("[-+]?\\d+") &&
                            Integer.parseInt(split[FIRST_DELIMITER_IN_ARRAY]) >= ZERO_PARAMETER) {
                        return false;
                    }
                }
                if (split.length >= THIRD_DELIMITER_IN_ARRAY) {

                    if (split[FIRST_DELIMITER_IN_ARRAY].matches("[-+]?\\d+")) {
                        return checksIfTheMonthEntered(isMonth, split, FIRST_DELIMITER_IN_ARRAY, SECOND_DELIMITER_IN_ARRAY);
                    } else {
                        return checksIfTheMonthEntered(isMonth, split, SECOND_DELIMITER_IN_ARRAY, FIRST_DELIMITER_IN_ARRAY);
                    }
                } else {
                    return true;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(DATA_MISSED);
            return true;
        } catch (NumberFormatException wrongInput) {
            System.out.println(WRONG_INPUT);
            return true;
        }
    }

    private static boolean checksIfTheMonthEntered(boolean isMonth, String[] split, int firstDelimiterInArray, int secondDelimiterInArray) {
        int day = Integer.parseInt(split[firstDelimiterInArray]);
        int year = Integer.parseInt(split[THIRD_DELIMITER_IN_ARRAY]);
        for (int separator = FIRST_ARRAY_INDEX; separator < NAME_OF_THE_MONTH.length; separator++) {
            if (split[secondDelimiterInArray].toLowerCase(Locale.ROOT).equals(NAME_OF_THE_MONTH[separator].toLowerCase(Locale.ROOT))) {
                if (day > START_POINT_DAY && year >= FIRST_YEAR_IN_ERA) {
                    for (String numberOfMonth : NUMBERS_FULL_MONTH) {
                        if (separator + ONE_INDEX == Integer.parseInt(numberOfMonth)) {
                            if (day <= COMMON_DAYS_IN_FULL_MONTH)
                                isMonth = true;
                        }
                    }
                    for (String numberOfMonth : NUMBERS_SHORT_MONTH) {
                        if (separator + ONE_INDEX == Integer.parseInt(numberOfMonth)) {
                            if (day <= COMMON_DAYS_IN_SHORT_MONTH)
                                isMonth = true;
                        }
                    }
                    if (split[secondDelimiterInArray].toLowerCase(Locale.ROOT).equals(FEBRUARY)) {
                        if (day == COMMON_DAYS_IN_FEBRUARY_LEAP_YEAR && (isLeapYear(year)))
                            isMonth = true;
                        if (day <= COMMON_DAYS_IN_FEBRUARY_ASTRONOMICAL_YEAR)
                            isMonth = true;
                    }
                }
            }
        }
        return !isMonth;
    }
}
