package ua.com.alevel.util;

import ua.com.alevel.properties.Properties;

import static java.lang.System.out;
import static ua.com.alevel.util.ConstantsApplication.*;

public class ProgramProperties {

    public static void printsResultMessageProcessDuration(Properties program) {
        out.println(MESSAGE_DURATION_PROCESS + program.durationOfTheProcess);
    }

    public static void printsResultMessageOptimisticPublication(Properties program) {
        out.println(MESSAGE_OPTIMISTIC_PUBLICATION + program.optimisticPublication);
    }

    public static void printsMessageDate(Properties program) {
        out.println(MESSAGE_DATE + program.date);
    }

    public static void printsMessageOfTheResultNotification(Properties program) {
        out.println(MESSAGE_RESULT_NOTIFICATION + program.resultNotification);
    }

    public static void printsMessageBranchProgram(Properties program) {
        out.println(MESSAGE + program.branchMessages);
    }
}
