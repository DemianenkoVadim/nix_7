package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dataclass.Date;
import ua.com.alevel.exception.DateException;

import java.util.Locale;

import static ua.com.alevel.controllers.CalendarController.definesAnArrayDivisor;
import static ua.com.alevel.util.ClassConstants.*;

public class CheckFormat {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public String convertsDateToString(Date dateInCalendar, String userChoice) {
        LOGGER_INFO.info("Starting convert Data into string representation");
        String dateString = NO_DELIMITER;
        switch (userChoice) {
            case USER_CHOOSE_FIRST_POINT:
                for (int nameOfTheMonth = FIRST_ARRAY_INDEX; nameOfTheMonth < NAME_OF_THE_MONTH.length; ++nameOfTheMonth) {
                    if (nameOfTheMonth == (dateInCalendar.getMonth() - ONE_ARRAY_INDEX))
                        dateString += NAME_OF_THE_MONTH[nameOfTheMonth] + SPACE;
                }
                if (dateInCalendar.getDay() == START_POINT_DAY) dateString += ONE_DAY_PLUS;
                dateString += dateInCalendar.getDay() + SPACE;
                dateString += dateInCalendar.getYear() + SPACE;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getHours()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getMinutes()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getSeconds()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToTripleZero(dateInCalendar.getMilliseconds());
                return dateString;
            case USER_CHOOSE_SECOND_POINT:
                if (dateInCalendar.getDay() == START_POINT_DAY) dateString += ONE_DAY_PLUS;
                dateString += dateInCalendar.getDay() + SPACE;

                for (int nameOfTheMonth = FIRST_ARRAY_INDEX; nameOfTheMonth < NAME_OF_THE_MONTH.length; ++nameOfTheMonth) {
                    if (nameOfTheMonth == (dateInCalendar.getMonth() - ONE_MONTH))
                        dateString += NAME_OF_THE_MONTH[nameOfTheMonth] + SPACE;
                }
                dateString += dateInCalendar.getYear() + SPACE;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getHours()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getMinutes()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getSeconds()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToTripleZero(dateInCalendar.getMilliseconds());
                return dateString;
            case USER_CHOOSE_THIRD_POINT:
                if (dateInCalendar.getDay() == START_POINT_DAY) dateString += ONE_DAY_PLUS;
                dateString += dateInCalendar.getDay() + SEPARATOR_IN_THE_ENTERED_DATE_SLASH;
                for (int nameOfTheMonth = FIRST_ARRAY_INDEX; nameOfTheMonth < NAME_OF_THE_MONTH.length; ++nameOfTheMonth) {
                    if (nameOfTheMonth == (dateInCalendar.getMonth() - ONE_MONTH))
                        dateString += dateInCalendar.getMonth() + SEPARATOR_IN_THE_ENTERED_DATE_SLASH;
                }
                dateString += dateInCalendar.getYear() + SPACE;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getHours()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getMinutes()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getSeconds()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToTripleZero(dateInCalendar.getMilliseconds());
                return dateString;
            case USER_CHOOSE_FOURS_POINT:
                if (dateInCalendar.getDay() == START_POINT_DAY) dateString += ONE_DAY_PLUS;
                for (int nameOfTheMonth = FIRST_ARRAY_INDEX; nameOfTheMonth < NAME_OF_THE_MONTH.length; ++nameOfTheMonth) {
                    if (nameOfTheMonth == (dateInCalendar.getMonth() - ONE_MONTH))
                        dateString += dateInCalendar.getMonth() + SEPARATOR_IN_THE_ENTERED_DATE_SLASH;
                }
                dateString += dateInCalendar.getDay() + SEPARATOR_IN_THE_ENTERED_DATE_SLASH;
                dateString += dateInCalendar.getYear() + SPACE;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getHours()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getMinutes()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToDoubleZero(dateInCalendar.getSeconds()) + REGEX_COLON;
                dateString += changesFormatDataOutputFromOneZeroToTripleZero(dateInCalendar.getMilliseconds());
                return dateString;
            default:
                System.out.println(WRONG_NUMBER);
                return dateString;
        }
    }

    public Date convertsStringLineInToDate(String userInputData) throws DateException {
        LOGGER_INFO.info("Starting converting string date format in date format");
        Date date;
        if (userInputData.contains(SEPARATOR_IN_THE_ENTERED_DATE_SLASH) || userInputData.contains(SEPARATOR_IN_THE_ENTERED_DATE_DASH)) {
            date = formatsLineWithDelimiter(userInputData);
        } else {
            date = formatUsersInputWithoutDelimiter(userInputData);
        }
        return date;
    }

    private Date formatsLineWithDelimiter(String userInput) throws NumberFormatException {
        LOGGER_INFO.info("Starting format user input date with input delimiter");
        Date date = new Date();
        String delimiter;
        if (userInput.contains(SEPARATOR_IN_THE_ENTERED_DATE_SLASH)) {
            delimiter = SEPARATOR_IN_THE_ENTERED_DATE_SLASH;
        } else {
            delimiter = SEPARATOR_IN_THE_ENTERED_DATE_DASH;
        }
        try {
            if (checksInputUserSomeData(definesAnArrayDivisor(delimiter, userInput)[FIRST_DELIMITER_IN_ARRAY])) {
                date.setDay(Integer.parseInt(definesAnArrayDivisor(delimiter, userInput)[FIRST_DELIMITER_IN_ARRAY]));
            }
            if (checksInputUserSomeData(definesAnArrayDivisor(delimiter, userInput)[SECOND_DELIMITER_IN_ARRAY])) {
                date.setMonth(Integer.parseInt(definesAnArrayDivisor(delimiter, userInput)[SECOND_DELIMITER_IN_ARRAY]));
            }
            if (checksInputUserSomeData(definesAnArrayDivisor(delimiter, userInput)[THIRD_DELIMITER_IN_ARRAY])) {
                date.setYear(Integer.parseInt(definesAnArrayDivisor(delimiter, userInput)[THIRD_DELIMITER_IN_ARRAY]));
            }
            if (definesAnArrayDivisor(delimiter, userInput).length > FOURTH_DELIMITER_IN_ARRAY)
                setsTheTime(date, definesAnArrayDivisor(delimiter, userInput)[FOURTH_DELIMITER_IN_ARRAY]);
            return date;
        } catch (NumberFormatException wrongInputData) {
            LOGGER_ERROR.error("Invalid input data. Checking your input information");
            throw new NumberFormatException(EXCEPTION_MESSAGE);
        }
    }

    public Date formatUsersInputWithoutDelimiter(String inputUserData) throws DateException {
        LOGGER_INFO.info("Starting format user input date without input delimiter");
        Date date = new Date();
        String[] dataSplit = inputUserData.split("[ ]");
        int countNumbersSpace = NO_SPACES;
        for (char delimiterElement : inputUserData.toCharArray()) {
            if (delimiterElement == EMPTY_SPACE) countNumbersSpace++;
        }
        if (countNumbersSpace == NO_SPACES) {
            if (checksInputUsersData(inputUserData, date, dataSplit))
                return date;
        }
        if (countNumbersSpace == ONE_SPACE && inputUserData.contains(REGEX_COLON)) {
            if (checksInputUsersData(inputUserData, date, dataSplit))
                return date;
        }
        try {
            boolean endingChecking = false;
            int indexOfMonth = START_POINT_TO_COUNTDOWN;
            for (String splits : dataSplit) {
                indexOfMonth++;
                for (int month = FIRST_ARRAY_INDEX; month < NAME_OF_THE_MONTH.length; month++) {
                    if (splits.toLowerCase(Locale.ROOT).equals(NAME_OF_THE_MONTH[month].toLowerCase(Locale.ROOT))) {
                        date.setMonth(month + ONE_MONTH);
                        endingChecking = true;
                        break;
                    }
                }
                if (endingChecking) break;
            }
            if (indexOfMonth == SECOND_DELIMITER_IN_ARRAY && dataSplit.length >= NUMBER_OF_DATE_SEPARATORS) {
                date.setDay(Integer.parseInt(dataSplit[FIRST_DELIMITER_IN_ARRAY]));
                date.setYear(Integer.parseInt(dataSplit[THIRD_DELIMITER_IN_ARRAY]));
            } else if (indexOfMonth == FIRST_DELIMITER_IN_ARRAY) {
                date.setDay(Integer.parseInt(dataSplit[SECOND_DELIMITER_IN_ARRAY]));
                date.setYear(Integer.parseInt(dataSplit[THIRD_DELIMITER_IN_ARRAY]));
            } else throw new DateException(WRONG_INPUT_DATE);

            if (inputUserData.contains(REGEX_COLON)) setsTheTime(date, dataSplit[dataSplit.length - ONE_INDEX]);
            return date;
        } catch (NumberFormatException incorrectNumber) {
            LOGGER_ERROR.error("Incorrect number format");
            throw new NumberFormatException();
        }
    }

    private boolean checksInputUsersData(String inputData, Date date, String[] splitsInInputUserData) {
        LOGGER_INFO.info("Starting checking users input data");
        if (splitsInInputUserData[FIRST_ARRAY_INDEX].matches("[-+]?\\d+") && Integer.parseInt(splitsInInputUserData[FIRST_ARRAY_INDEX]) >= ZERO_PARAMETER) {
            date.setMonth(ONE_MONTH);
            date.setDay(ONE_DAY);
            date.setDay(Integer.parseInt(splitsInInputUserData[FIRST_ARRAY_INDEX]));
            if (inputData.contains(REGEX_COLON))
                setsTheTime(date, splitsInInputUserData[splitsInInputUserData.length - ONE_ARRAY_INDEX]);
            return true;
        }
        return false;
    }

    public void setsTheTime(Date date, String timeData) throws NumberFormatException {
        LOGGER_INFO.info("Starting setting the time data");
        String[] dataSplit = timeData.split(REGEX_COLON);
        try {
            for (int delimiter = FIRST_ARRAY_INDEX; delimiter < dataSplit.length; ++delimiter) {
                if (dataSplit[delimiter].equals(NO_DELIMITER)) {
                    dataSplit[delimiter] = FORMAT_DATA_ONE_ZERO;
                }
                switch (delimiter) {
                    case FIRST_DELIMITER_IN_ARRAY:
                        date.setHours(Integer.parseInt(dataSplit[FIRST_DELIMITER_IN_ARRAY]));
                        break;
                    case SECOND_DELIMITER_IN_ARRAY:
                        date.setMinutes(Integer.parseInt(dataSplit[SECOND_DELIMITER_IN_ARRAY]));
                        break;
                    case THIRD_DELIMITER_IN_ARRAY:
                        date.setSeconds(Integer.parseInt(dataSplit[THIRD_DELIMITER_IN_ARRAY]));
                        break;
                    case FOURTH_DELIMITER_IN_ARRAY:
                        date.setMilliseconds(Integer.parseInt(dataSplit[FOURTH_DELIMITER_IN_ARRAY]));
                        break;
                }
            }
        } catch (NumberFormatException wrongTimeFormat) {
            LOGGER_ERROR.error("Invalid input data. Checking your input information");
            throw new NumberFormatException(EXCEPTION_MESSAGE);
        }
    }

    public boolean checksInputUserSomeData(String dataInformationInput) {
        return !dataInformationInput.equals(NO_DELIMITER);
    }

    public String changesFormatDataOutputFromOneZeroToDoubleZero(int dataValueToConvert) {
        if (dataValueToConvert < DATA_VALUE_DECA) return FORMAT_DATA_ONE_ZERO + dataValueToConvert;
        return dataValueToConvert + NO_DELIMITER;
    }

    public String changesFormatDataOutputFromOneZeroToTripleZero(int dataValueToConvert) {
        if (dataValueToConvert < DATA_VALUE_DECA) return DOUBLE_FORMAT_DOUBLE_ZERO + dataValueToConvert;
        if (dataValueToConvert < DATA_VALUE_HUNDRED) return FORMAT_DATA_ONE_ZERO + dataValueToConvert;
        return dataValueToConvert + NO_DELIMITER;
    }
}
