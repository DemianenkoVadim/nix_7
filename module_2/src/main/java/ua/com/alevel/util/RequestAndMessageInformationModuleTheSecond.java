package ua.com.alevel.util;

import static java.lang.System.out;
import static ua.com.alevel.util.Constants.*;

public class RequestAndMessageInformationModuleTheSecond {

    public static void printsRequestActonToDoInDataConverterApp() {
        for (String requestsInformation : REQUEST_TO_CHOOSE_ACTION_IN_DATA_CONVERTER_APP) {
            out.println(requestsInformation);
        }
        out.println();
    }

    public static void printsRequestActonToDoInUniqueFinderApp() {
        for (String requestsInformation : REQUEST_TO_CHOOSE_ACTION_IN_UNIQUE_NAME_FINDER) {
            out.println(requestsInformation);
        }
        out.println();
    }

    public static void printsRequestMainMenu() {
        for (String requestsInformationChoice : REQUEST_TO_CHOOSE_ONE_OF_THE_APP) {
            out.println(requestsInformationChoice);
        }
        out.println();
    }

    public static void printsIntroduceMessageDataConverter() {
        out.println(INTRODUCE_DATA_CONVERTER);
    }

    public static void printsIntroduceMessageUniqueNameFinder() {
        out.println(INTRODUCE_UNIQUE_NAME_FINDER);
    }

    public static void printsIntroduceMessageCheapestWayFinder() {
        out.println(INTRODUCE_CHEAPEST_WAY_FINDER);
    }

    public static void printsInformationFormatDataInput() {
        out.println(INFORMATION_DATA_FORMAT_INPUT);
    }

    public static void printsInformationListFormattedData() {
        out.println(INFORMATION_THE_DATA_FORMATTED_LIST);
    }

    public static void printsInformationNamesInput() {
        out.println(INFORMATION_UNIQUE_NAME_FINDER_INPUT);
    }

    public static void printsMessageInformationUniqueName() {
        out.println(INFORMATION_FIRST_UNIQUE_NAME);
    }

    public static void printsMessageInformationListNameFromFile() {
        out.println(INFORMATION_OF_LIST_NAMES_OF_THE_FILE);
    }

    public static void printsMessageInputInformation() {
        out.println(INFORMATION_INPUT);
    }

    public static void printsMessageOutputInformation() {
        out.println(INFORMATION_OUTPUT);
    }

    public static void printsMessageMenuInformation() {
        System.out.println(NAME_MENU);
    }
}
