package ua.com.alevel.service.requests;


import lombok.SneakyThrows;
import ua.com.alevel.controller.FootballTeamController;

import java.io.BufferedReader;

import static java.lang.System.err;
import static java.lang.System.out;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class RequestFootballTeamInformation {

    private int footballTeamFounderYear;

    @SneakyThrows
    public String requestsFootballTeamID(BufferedReader readerFromConsole) {
        out.println(INPUT_ID_FOOTBALL_TEAM);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public String requestsFootballTeamName(BufferedReader readerFromConsole) {
        out.println(NAME_TEAM);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public String requestsFootballTeamCountry(BufferedReader readerFromConsole) {
        out.println(TEAM_COUNTRY);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public String requestsFootballTeamTown(BufferedReader readerFromConsole) {
        out.println(TEAM_TOWN);
        return readerFromConsole.readLine();
    }

    @SneakyThrows
    public int requestsFootballTeamYearFounded(BufferedReader readerFromConsole) {
        out.println(FOUNDED_YEAR);
        String ageString = readerFromConsole.readLine();
        return checksCorrectInputFootballTeamFoundedYear(ageString);
    }

    @SneakyThrows
    public int checksCorrectInputFootballTeamFoundedYear(String yearString) {
        if (yearString.matches(CHECKING_DIGIT_INPUT)) {
            footballTeamFounderYear = Integer.parseInt(yearString);
        }
        if (footballTeamFounderYear >= TEAM_MAXIMUM_YEAR_FOUNDED || footballTeamFounderYear <= TEAM_MINIMUM_YEAR_FOUNDED) {
            err.println(WRONG_INPUT_YEAR_FOUNDED_TEAM);
            out.println();
            new FootballTeamController().startFootballTeam();
        }
        return footballTeamFounderYear;
    }
}
