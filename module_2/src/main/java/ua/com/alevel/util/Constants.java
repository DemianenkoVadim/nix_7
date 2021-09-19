package ua.com.alevel.util;

public class Constants {

    public static final int START_POINT_YEAR = 0;
    public static final int START_POINT_MONTH = 0;
    public static final int START_POINT_DAY = 0;
    public static final int REMAINDER_OF_THE_DIVISION = 0;
    public static final int FIRST_LIST_DATA = 0;
    public static final int FIRST_DATA_IN_ARRAY = 0;
    public static final int ZERO_INDEX = 0;
    public static final int SAME_DATA = 0;
    public static final int FIRST_DATA = 1;
    public static final int ONE_INDEX = 1;
    public static final int SECOND_DATA_IN_ARRAY = 1;
    public static final int SECOND_DATA = 2;
    public static final int NEXT_WAY = 2;
    public static final int HIGH_RISE_YEAR_EVERY_FOUR_YEARS = 4;
    public static final int MULTIPLICITY_ONE_HUNDRED = 100;
    public static final int MULTIPLICITY_FOUR_HUNDRED = 400;

    public static final int COMMON_DAYS_IN_FEBRUARY_ASTRONOMICAL_YEAR = 28;
    public static final int COMMON_DAYS_IN_FEBRUARY_LEAP_YEAR = 29;
    public static final int COMMON_DAYS_IN_SHORT_MONTH = 30;
    public static final int COMMON_DAYS_IN_FULL_MONTH = 31;
    public static final int COMMON_NUMBER_OF_MONTH_IN_YEAR = 12;

    public static final String NUMBER_OF_THE_MONTH_FEBRUARY_IN_THE_CALENDAR = "02";
    public static final String[] NUMBERS_FULL_MONTH = {"01", "03", "05", "07", "08", "10", "12"};
    public static final String[] NUMBERS_SHORT_MONTH = {"04", "06", "09", "11"};

    public static final String FORMAT_DATE_MONTH_DAY_YEAR = "(?<month>\\d{2})-(?<day>\\d{2})-(?<year>\\d{4})";
    public static final String FORMAT_DATE_DAY_MONTH_YEAR = "(?<day>\\d{2})/(?<month>\\d{2})/(?<year>\\d{4})";
    public static final String FORMAT_DATE_YEAR_MONTH_DAY = "(?<year>\\d{4})/(?<month>\\d{2})/(?<day>\\d{2})";

    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String YEAR = "year";
    public static final String EMPTY_STRING = "";
    public static final String SPACE = " ";

    public static final String USER_CHOOSE_FIRST_POINT = "1";
    public static final String USER_CHOOSE_SECOND_POINT = "2";
    public static final String USER_CHOOSE_THIRD_POINT = "3";

    public static final String[] REQUEST_TO_CHOOSE_ACTION_IN_DATA_CONVERTER_APP = {
            "Load file Date.txt file          - PRESS - 1",
            "Enter data in console            - PRESS - 2",
            "Exit to previous menu            - PRESS - ANY OTHER SYMBOL",
    };

    public static final String[] REQUEST_TO_CHOOSE_ACTION_IN_UNIQUE_NAME_FINDER = {
            "Load file names.txt file         - PRESS - 1",
            "Enter data in console            - PRESS - 2",
            "Exit to previous menu            - PRESS - ANY OTHER SYMBOL",
    };

    public static final String[] REQUEST_TO_CHOOSE_ONE_OF_THE_APP = {
            "List of dates in a list without delimiters           - PRESS - 1",
            "Find first unique name                               - PRESS - 2",
            "Find the cheapest way                                - PRESS - 3",
            "Exit the application                                 - PRESS - ANY OTHER SYMBOL",
    };

    public static final String NAME_MENU = "------- MAIN MENU BAR ------";
    public static final String INTRODUCE_DATA_CONVERTER = "----- DATA CONVERTER -----";
    public static final String INTRODUCE_UNIQUE_NAME_FINDER = "----- UNIQUE DATA -----";
    public static final String INTRODUCE_CHEAPEST_WAY_FINDER = "----- CHEAPEST WAY FINDER -----";

    public static final String INFORMATION_DATA_FORMAT_INPUT = "Enter data's in this format -> (2020/04/05 04-05-2020 05/04/2020)";
    public static final String INFORMATION_THE_DATA_FORMATTED_LIST = "List formatted data with wright first form: ";
    public static final String INFORMATION_UNIQUE_NAME_FINDER_INPUT = "Enter names with space, for example -> (Vadim Evgen Michael David)";
    public static final String INFORMATION_FIRST_UNIQUE_NAME = "First unique name from list: ";
    public static final String INFORMATION_OF_LIST_NAMES_OF_THE_FILE = "List names from file names.txt";
    public static final String INFORMATION_INPUT = "Input - ";
    public static final String INFORMATION_OUTPUT = "Output - ";
    public static final String INDENTION = "\n";
}
