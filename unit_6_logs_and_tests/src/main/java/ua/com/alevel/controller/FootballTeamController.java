package ua.com.alevel.controller;

import ua.com.alevel.entity.FootballTeam;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;
import ua.com.alevel.service.FootballTeamService;
import ua.com.alevel.service.requests.RequestFootballTeamInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;
import static ua.com.alevel.controller.UserInterfaceController.printsNavigator;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class FootballTeamController {

    private static final FootballTeamService footballTeam = new FootballTeamService();
    private static final NonExistentIDException idException = new NonExistentIDException();
    private static final EmptyRegistrationBaseException baseException = new EmptyRegistrationBaseException();
    private static final RequestFootballTeamInformation requestFootballTeamData = new RequestFootballTeamInformation();

    public void startFootballTeam() throws Exception {
        BufferedReader readerFromConsole = new BufferedReader(new InputStreamReader(in));
        String userChoice;
        try {
            runNavigationFootballTeam();
            while ((userChoice = readerFromConsole.readLine()) != null) {
                launchCrudMethodsForFootballTeam(userChoice, readerFromConsole);
                userChoice = readerFromConsole.readLine();
                if (userChoice.equals(ZERO_POSITION)) {
                    new UserInterfaceController().startsTheProgram();
                }
                launchCrudMethodsForFootballTeam(userChoice, readerFromConsole);
            }
        } catch (IOException wrongUserInput) {
            out.println(wrongUserInput.getMessage());
        }
    }

    private void runNavigationFootballTeam() {
        printsNavigator(INTRODUCE_TEAM_BASE, CREATE_FOOTBALL_TEAM, UPDATE_FOOTBALL_TEAM,
                DELETE_FOOTBALL_TEAM, FIND_BY_ID_FOOTBALL_TEAM, FIND_ALL_FOOTBALL_TEAM, EXIT_OUT_TO_PREVIOUS_MENU);
    }

    private void launchCrudMethodsForFootballTeam(String userChoicePosition, BufferedReader readerFromConsole) throws Exception {
        switch (userChoicePosition) {
            case USER_CHOOSE_FIRST_POINT:
                createFootballTeam(readerFromConsole);
                break;
            case USER_CHOOSE_SECOND_POINT:
                updateFootballTeam(readerFromConsole);
                break;
            case USER_CHOOSE_THIRD_POINT:
                deleteFootballTeam(readerFromConsole);
                break;
            case USER_CHOOSE_FOURS_POINT:
                findAndPrintByIdFootballTeam(readerFromConsole);
                break;
            case USER_CHOOSE_FIVES_POINT:
                findAllFootballsTeam();
                break;
            case USER_CHOOSE_ZERO_POINT_TEN:
                new UserInterfaceController().startsTheProgram();
                break;
        }
        runNavigationFootballTeam();
    }


    void createFootballTeam(BufferedReader readerFromConsole) throws Exception {
        out.println(BAR_CREATE_TEAM);
        try {
            createsInfoOfFootballTeam(readerFromConsole);
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
            out.println();
        }
    }

    private void updateFootballTeam(BufferedReader readerFromConsole) throws Exception {
        out.println(BAR_UPDATE_TEAM);
        try {
            footballTeam.findFootballTeamFromRegistrationListById(requestFootballTeamData.requestsFootballTeamID(readerFromConsole));
            createsInfoOfFootballTeam(readerFromConsole);
        } catch (IOException e) {
            err.println(e.getMessage());
            out.println();
        } catch (NonExistentIDException wrongTeamId) {
            idException.printErrorNonExistentIDExceptionMessage(wrongTeamId);
        }
    }

    private void createsInfoOfFootballTeam(BufferedReader readerFromConsole) throws Exception {
        FootballTeam team = new FootballTeam();
        team.setNameOfTheFootballTeam(requestFootballTeamData.requestsFootballTeamName(readerFromConsole));
        team.setLocationCountryFootballTeam(requestFootballTeamData.requestsFootballTeamCountry(readerFromConsole));
        team.setLocationTownFootballTeam(requestFootballTeamData.requestsFootballTeamTown(readerFromConsole));
        team.setYearOfFootballTeamWasFounded(requestFootballTeamData.requestsFootballTeamYearFounded(readerFromConsole));
        footballTeam.createFootballTeamInRegistrationBase(team);
        out.println(DONE);
        out.println();
        startFootballTeam();
    }

    private void deleteFootballTeam(BufferedReader readerFromConsole) {
        out.println(BAR_DELETE_TEAM);
        try {
            footballTeam.findAllFootballTeamsFromRegistrationBase();
            footballTeam.deleteFootballTeamFromRegistrationBase(requestFootballTeamData.requestsFootballTeamID(readerFromConsole));
            out.println();
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
        } catch (NonExistentIDException wrongTeamID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongTeamID);
        }
    }

    private void findAndPrintByIdFootballTeam(BufferedReader readerFromConsole) {
        out.println(BAR_FIND_BY_ID_TEAM);
        try {
            FootballTeam team = footballTeam.findFootballTeamFromRegistrationListById(requestFootballTeamData.requestsFootballTeamID(readerFromConsole));
            out.println(FOOTBALL_TEAM + team);
            out.println();
        } catch (NonExistentIDException wrongTeamId) {
            idException.printErrorNonExistentIDExceptionMessage(wrongTeamId);
        }
    }

    protected void findAllFootballsTeam() {
        try {
            footballTeam.findAllFootballTeamsFromRegistrationBase();
            out.println();
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
        }
    }
}
