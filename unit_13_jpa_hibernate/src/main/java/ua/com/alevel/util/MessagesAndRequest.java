package ua.com.alevel.util;

import static java.lang.System.out;
import static ua.com.alevel.util.ConstantsApplicationUnit_13.*;

public class MessagesAndRequest {

    public static void printsApplicationHeader() {
        out.println(FIND_OUT_NEXT_LESSON);
    }

    public static void printsRequestToChooseOption() {
        out.println(CHOOSE_ACTION);
    }

    public static void printsRequestToChooseActionToFindNearestLesson() {
        out.println(CHOOSE_TO_FIND_OUT_NEAREST_LESSON);
    }

    public static void printsRequestToExitTheApplication() {
        out.println(CHOOSE_TO_EXIT_THE_APPLICATION);
    }

    public static void printsRequestToFindOutFullListOfStudents() {
        out.println(CHOOSE_TO_FIND_OUT_ALL_STUDENTS);
    }
}
