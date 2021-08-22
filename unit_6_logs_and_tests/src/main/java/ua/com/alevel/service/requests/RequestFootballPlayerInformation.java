package ua.com.alevel.service.requests;

import lombok.SneakyThrows;
import ua.com.alevel.controller.FootballPlayerController;

import java.io.BufferedReader;

import static java.lang.System.err;
import static java.lang.System.out;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class RequestFootballPlayerInformation {

    private int footballPlayerAge;

    @SneakyThrows
    public String requestsFootballPlayerID(BufferedReader readerFromConsole) {
        out.println(INPUT_ID_FOOTBALL_PLAYER);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public String requestsFootballPlayerFirstName(BufferedReader readerFromConsole) {
        out.println(FIRST_NAME);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public String requestsFootballPlayerLastName(BufferedReader readerFromConsole) {
        out.println(LAST_NAME);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public String requestsFootballPlayerPosition(BufferedReader readerFromConsole) {
        out.println(FOOTBALL_POSITION);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public String requestsFootballPlayerNationality(BufferedReader readerFromConsole) {
        out.println(NATIONALITY);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public int requestsFootballPlayerAge(BufferedReader readerFromConsole) {
        out.println(AGE);
        String ageString = readerFromConsole.readLine();
        return checksCorrectInputFootballPlayerAge(ageString);
    }

    @SneakyThrows
    public int checksCorrectInputFootballPlayerAge(String ageString) {
        if (ageString.matches(CHECKING_DIGIT_INPUT)) {
            footballPlayerAge = Integer.parseInt(ageString);
        }
        if (footballPlayerAge >= PLAYER_MAXIMUM_AGE || footballPlayerAge <= PLAYER_MINIMUM_AGE) {
            err.println(WRONG_INPUT_AGE);
            out.println();
            new FootballPlayerController().startsFootballPlayer();
        }
        return footballPlayerAge;
    }
}
