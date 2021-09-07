package ua.com.alevel.controllers;

import ua.com.alevel.dataclass.Date;
import ua.com.alevel.util.CalculatorPassedMilliseconds;
import ua.com.alevel.util.InverterFromMilliseconds;
import ua.com.alevel.util.TimeUnitsToMillisecondsConverter;

import java.util.ArrayList;
import java.util.Scanner;

import static ua.com.alevel.service.RequestsAndMessagesInformation.*;
import static ua.com.alevel.util.ClassConstants.*;

public class SupportController {

    public static boolean datesAreSorted;
    private static final Scanner userInput = new Scanner(System.in);
    public static boolean isTime = true;

    public static ArrayList<Date> sortsInputDates(ArrayList<Date> inputDates, String userChooseToSortDates) {
        int listSize = inputDates.size();
        switch (userChooseToSortDates) {
            case USER_CHOOSE_ZERO_POINT:
                comparesDatesDescending(inputDates, listSize);
                return inputDates;
            case USER_CHOOSE_FIRST_POINT:
                comparesDatesInAscendingOrder(inputDates, listSize);
                return inputDates;
            default:
                return inputDates;
        }
    }

    public static void comparesDatesInAscendingOrder(ArrayList<Date> datesInputByUser, int numberOfDatesEntered) {
        do {
            datesAreSorted = true;
            for (int userInputData = FIRST_ARRAY_INDEX; userInputData < numberOfDatesEntered - ONE_ARRAY_INDEX; userInputData++) {
                if (CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(datesInputByUser.get(userInputData)) >
                        CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(datesInputByUser.get(userInputData + ONE_INDEX))) {
                    Date date = datesInputByUser.get(userInputData);
                    datesInputByUser.set(userInputData, datesInputByUser.get(userInputData + ONE_ARRAY_INDEX));
                    datesInputByUser.set(userInputData + ONE_ARRAY_INDEX, date);
                    datesAreSorted = false;
                }
            }
        } while (!datesAreSorted);
    }

    public static void comparesDatesDescending(ArrayList<Date> dates, int numberOfDatesEntered) {
        do {
            datesAreSorted = true;
            for (int userInputData = FIRST_ARRAY_INDEX; userInputData < numberOfDatesEntered - ONE_ARRAY_INDEX; userInputData++) {
                if (CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(dates.get(userInputData)) <
                        CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(dates.get(userInputData + ONE_INDEX))) {
                    Date date = dates.get(userInputData);
                    dates.set(userInputData, dates.get(userInputData + ONE_ARRAY_INDEX));
                    dates.set(userInputData + ONE_ARRAY_INDEX, date);
                    datesAreSorted = false;
                }
            }
        } while (!datesAreSorted);
    }

    public static void printsDifferenceBetweenDatesInChosenValues(long milliseconds) {
        boolean requestOfTypeResultEnds;
        do {
            requestOfTypeResultEnds = false;
            printsMessageTypeShownResult();
            requestToChooseParameterTime();
            printsMessageToExitTheProgram();
            String userInputType = userInput.nextLine();
            switch (userInputType) {
                case USER_CHOOSE_FIRST_POINT:
                    System.out.println(DIFFERENCE_IN_MILLISECONDS + milliseconds);
                    break;
                case USER_CHOOSE_SECOND_POINT:
                    System.out.println(DIFFERENCE_IN_SECONDS + TimeUnitsToMillisecondsConverter.convertsMillisecondsToSeconds(milliseconds));
                    break;
                case USER_CHOOSE_THIRD_POINT:
                    System.out.println(DIFFERENCE_IN_MINUTES + TimeUnitsToMillisecondsConverter.convertsMillisecondsToMinutes(milliseconds));
                    break;
                case USER_CHOOSE_FOURS_POINT:
                    System.out.println(DIFFERENCE_IN_HOURS + TimeUnitsToMillisecondsConverter.convertsMillisecondsToHours(milliseconds));
                    break;
                case USER_CHOOSE_FIVES_POINT:
                    System.out.println(DIFFERENCE_IN_DAYS + TimeUnitsToMillisecondsConverter.convertsMillisecondsToDays(milliseconds));
                    break;
                case USER_CHOOSE_SIX_POINT:
                    System.out.println(DIFFERENCE_IN_YEARS + InverterFromMilliseconds.transfersMillisecondsToYears(milliseconds));
                    break;
                default:
                    requestOfTypeResultEnds = true;
            }
        } while (!requestOfTypeResultEnds);
    }

    public static long measuresAndConvertsIntoMilliseconds() {
        requestToChooseParameterTime();
        String userChoiceInput = userInput.nextLine();
        printsMessageToRequestInputValue();
        boolean firstOne = true;
        String valueOfDate;
        do {
            if (!firstOne) {
                System.out.println(WRONG_NUMBER);
                printsMessageToRequestInputValue();
            }
            valueOfDate = userInput.nextLine();
            firstOne = false;
        } while (!valueOfDate.matches("[-+]?\\d+"));

        long valueInLong = Long.parseLong(valueOfDate);

        switch (userChoiceInput) {
            case USER_CHOOSE_FIRST_POINT:
                return valueInLong;
            case USER_CHOOSE_SECOND_POINT:
                return TimeUnitsToMillisecondsConverter.convertsSecondsToMilliseconds(valueInLong);
            case USER_CHOOSE_THIRD_POINT:
                return TimeUnitsToMillisecondsConverter.convertsMinutesToMilliseconds((valueInLong));
            case USER_CHOOSE_FOURS_POINT:
                return TimeUnitsToMillisecondsConverter.convertsHoursToMilliseconds((valueInLong));
            case USER_CHOOSE_FIVES_POINT:
                return TimeUnitsToMillisecondsConverter.convertsDaysToMilliseconds((valueInLong));
            case USER_CHOOSE_SIX_POINT:
                return CalculatorPassedMilliseconds.calculatesTotalPassedMillisecondsUserSpecifiedYearSinceBeginningOfNewEra(((int) valueInLong));
            default:
                printsMessageIncorrectSymbolInput();
        }
        return ZERO_MILLISECONDS;
    }

    public static void checksFormatTime(String input) {
        if (chooseDelimiterInData(determinesTheDelimiter(input), input).length > NUMBER_OF_DATE_SEPARATORS) {
            String[] splitTimeInData = chooseDelimiterInData(determinesTheDelimiter(input), input)[NUMBER_OF_DATE_SEPARATORS].split(REGEX_COLON);
            for (int separator = FIRST_ARRAY_INDEX; separator < splitTimeInData.length; separator++) {
                if (splitTimeInData[separator].equals(NO_DELIMITER)) {
                    splitTimeInData[separator] = FORMAT_DATA_ONE_ZERO;
                }
            }
            checksFormatOfTimeInFullDataTime(splitTimeInData);
        }
    }

    public static void checksFormatOfTimeInFullDataTime(String[] splitTimeInData) {
        checksFormatOFTimeInDataDays(splitTimeInData);
        checksFormatOfTimeInDataMinutes(splitTimeInData);
        checksFormatOfTimeInDataSeconds(splitTimeInData);
        checksFormatOfTimeInDataMilliseconds(splitTimeInData);
    }

    public static void checksFormatOFTimeInDataDays(String[] splitTimeInData) {
        if (Integer.parseInt(splitTimeInData[FIRST_DELIMITER_IN_ARRAY]) > FULL_NUMBER_OF_HOURS_IN_DATE_FORMAT_IN_DAY ||
                Integer.parseInt(splitTimeInData[FIRST_DELIMITER_IN_ARRAY]) < ZERO_PARAMETER) {
            isTime = false;
        }
    }

    public static void checksFormatOfTimeInDataMinutes(String[] splitTimeInData) {
        if (splitTimeInData.length > TWO_DELIMITERS) {
            if (Integer.parseInt(splitTimeInData[SECOND_DELIMITER_IN_ARRAY]) > FULL_NUMBER_OF_MINUTES_IN_DATE_FORMAT_IN_HOURS ||
                    Integer.parseInt(splitTimeInData[SECOND_DELIMITER_IN_ARRAY]) < ZERO_PARAMETER) {
                isTime = false;
            }
        }
    }

    public static void checksFormatOfTimeInDataSeconds(String[] splitTimeInData) {
        if (splitTimeInData.length > THREE_DELIMITERS) {
            if (Integer.parseInt(splitTimeInData[THIRD_DELIMITER_IN_ARRAY]) > FULL_NUMBER_OF_SECONDS_IN_DATE_FORMAT_IN_MINUTE ||
                    Integer.parseInt(splitTimeInData[THIRD_DELIMITER_IN_ARRAY]) < ZERO_PARAMETER) {
                isTime = false;
            }
        }
    }

    public static void checksFormatOfTimeInDataMilliseconds(String[] splitTimeInData) {
        if (splitTimeInData.length > FOUR_DELIMITERS) {
            if (Integer.parseInt(splitTimeInData[FOURTH_DELIMITER_IN_ARRAY]) > FULL_NUMBER_OF_MILLISECONDS_IN_DATE_FORMAT_IN_SECOND ||
                    Integer.parseInt(splitTimeInData[FOURTH_DELIMITER_IN_ARRAY]) < ZERO_PARAMETER) {
                isTime = false;
            }
        }
    }

    public static boolean checksFormattingOfTheDataTime(String userInput) {
        try {
            if (userInput.contains(SEPARATOR_IN_THE_ENTERED_DATE_SLASH) || userInput.contains(SEPARATOR_IN_THE_ENTERED_DATE_DASH)) {
                checksFormatTime(userInput);
            }
        } catch (ArrayIndexOutOfBoundsException exceptionOfBorderArray) {
            System.out.println(DATA_MISSED);
            return false;
        } catch (NumberFormatException exceptionNumber) {
            System.out.println(WRONG_INPUT);
            return false;
        }
        return false;
    }

    public static String determinesTheDelimiter(String userInput) {
        if (userInput.contains(SEPARATOR_IN_THE_ENTERED_DATE_SLASH))
            return SEPARATOR_IN_THE_ENTERED_DATE_SLASH;
        else
            return SEPARATOR_IN_THE_ENTERED_DATE_DASH;
    }

    protected static String[] chooseDelimiterInData(String delimiterInData, String userInput) {
        String[] splitsInData = new String[NUMBER_OF_DELIMITERS_IN_DATA];
        switch (delimiterInData) {
            case SEPARATOR_IN_THE_ENTERED_DATE_SLASH:
                splitsInData = userInput.split("[/ ]");
                break;
            case SEPARATOR_IN_THE_ENTERED_DATE_DASH:
                splitsInData = userInput.split("[- ]");
                break;
        }
        return splitsInData;
    }
}
