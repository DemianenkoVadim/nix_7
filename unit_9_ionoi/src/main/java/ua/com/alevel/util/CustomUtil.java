package ua.com.alevel.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.util.Constant.*;

public class CustomUtil {

    public static void printsRequestsOrInformationMessages(String someStatement) {
        System.out.println(someStatement);
    }

    public static String getString() {
        String str = EMPTY_STRING;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            str = reader.readLine().trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static int getInt() {
        try {
            return Integer.parseInt(getString());
        } catch (NumberFormatException e) {
            System.out.println(INPUT_RIGHT_CHOOSE);
        }
        return getInt();
    }

    public static boolean checksRightFilmDuration(int filmDuration) {
        if (filmDuration < LOW_CASE_LIMIT_DURATION_FILM) {
            printsRequestsOrInformationMessages(TO_SHORT_FILM);
            return false;
        } else if (filmDuration > UP_CASE_LIMIT_DURATION_FILM) {
            printsRequestsOrInformationMessages(TO_LONG_FILM);
            return false;
        }
        return true;
    }

    public static boolean checksFilmTitleInput(String filmTitle) {
        if (filmTitle.length() < LOW_CASE_LIMIT_NAME_FILM_TITLE_FILM ||
                filmTitle.length() > UP_CASE_LIMIT_NAME_FILM_TITLE_FILM) {
            printsRequestsOrInformationMessages(CHECK_FILM_TITLE);
            return false;
        } else if (!filmTitle.matches(TITLE_CHECKING)) {
            printsRequestsOrInformationMessages(FILM_TITLE_VERIFICATION);
            return false;
        }
        return true;
    }

    public static boolean checksActorFullName(String fullName) {
        if (fullName.length() < LOW_CASE_LIMIT_NAME_FILM_ACTOR ||
                fullName.length() > UP_CASE_LIMIT_NAME_FILM_ACTOR) {
            printsRequestsOrInformationMessages(NO_FULL_NAME_ACTOR);
            return false;
        } else if (!fullName.matches(NAME_CHECKING)) {
            printsRequestsOrInformationMessages(NO_FULL_NAME_ACTOR);
            return false;
        }
        return true;
    }
}
