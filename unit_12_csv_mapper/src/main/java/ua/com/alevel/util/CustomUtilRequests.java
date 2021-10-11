package ua.com.alevel.util;

import static java.lang.System.out;
import static ua.com.alevel.util.ApplicationConstants.*;

public class CustomUtilRequests {

    public static void printsRequestsOrInformationMessages(String someStatement) {
        out.println(someStatement);
    }

    public static void printsMessageRequestToChooseARow() {
        out.print(MESSAGE_REQUEST_ROW_NUMBER);
    }

    public static void printsMessageRequestToChooseAColumn() {
        out.println(MESSAGE_REQUEST_COLUMN_NUMBER);
    }

    public static void printsMessageRequestToChooseTitleName() {
        out.println(MESSAGE_REQUEST_TITLE_NAME);
    }
}
