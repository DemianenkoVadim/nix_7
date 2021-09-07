package ua.com.alevel.service;

import static java.lang.System.*;
import static ua.com.alevel.util.ClassConstants.*;

public class RequestsAndMessagesInformation {

    public static void printsMessageRequestInformationToAddDateOrExitTheApplication() {
        out.println(REQUEST_TO_ADD_DATE);
        out.println(REQUEST_TO_EXIT_THE_APPLICATION);
        out.println();
    }

    public static void printsMessageRequestInformationToSortDates() {
        out.println(REQUEST_INFORMATION_OF_DECREASING_SORT_DATES);
        out.println(REQUEST_INFORMATION_OF_INCREASING_SORT_DATES);
        out.println(REQUEST_INFORMATION_OF_DISABLE);
        out.println();
    }

    public static void printAvailableFormats() {
        out.println(CHOOSE_INPUT_FORMATS);
        for (String inputDataRequest : INPUT_OUTPUT_FORMATS) {
            out.println(inputDataRequest);
        }
        out.println();
    }

    public static void printsIntroduceApplication() {
        out.println(INTRODUCE);
        out.println();
    }

    public static void printsApplicationCapabilities() {
        printsIntroduceApplication();
        for (String capabilities : APPLICATION_CAPABILITIES) {
            out.println(capabilities);
        }
        out.println();
    }

    public static void printsRequestToDo() {
        out.println(REQUEST_TO_DO);
        out.println();
    }

    public static void printsRequestToEnterFirstDate() {
        out.println(REQUEST_TO_INPUT_FIRST_DATE);
        out.println();
    }

    public static void printsRequestToEnterSecondDate() {
        out.println(REQUEST_TO_INPUT_SECOND_DATE);
        out.println();
    }

    public static void printsMessageWrongInput() {
        out.println(WRONG_INPUT_DATE);
        out.println();
    }

    public static void printsMessageToEnterTheDate() {
        out.println(REQUEST_TO_INPUT_DATE);
        out.println();
    }

    public static void printsMessageToChooseFormatData() {
        out.println(REQUEST_INFORMATION_T0_OUTPUT_FORMAT_DATA);
        out.println();
    }

    public static void printsMessageTypeShownResult() {
        out.println(REQUEST_INFORMATION_SHOWN_THE_RESULT);
        out.println();
    }

    public static void printsMessageToExitTheProgram() {
        out.println(INFORMATION_TO_EXIT_THE_APPLICATION);
        out.println();
    }

    public static void printsMessageToRequestInputValue() {
        out.println(INPUT_SIGNIFICANCE);
        out.println();
    }

    public static void printsFormatDataCapabilities() {
        for (String formatDataCapabilities : REQUEST_OUTPUT_FORMAT_DATA) {
            out.println(formatDataCapabilities);
        }
        out.println();
    }

    public static void printsRequestInformationToAdd() {
        out.println(REQUEST_INFORMATION_TO_ADD);
    }

    public static void printsRequestInformationToSubtract() {
        out.println(REQUEST_INFORMATION_TO_SUBTRACT);
        out.println();
    }

    public static void printsMessageIncorrectSymbolInput() {
        out.println(WRONG_INPUT_CHOOSE);
    }

    public static void printsRequestToAddOrSubtract() {
        out.println(REQUEST_INFORMATION_TO_ADD_OR_SUBTRACT);
    }

    public static void requestToChooseParameterTime() {
        for (String timeParameter : REQUEST_TO_CHOOSE_PARAMETER_TIME) {
            out.println(timeParameter);
        }
        out.println();
    }
}
