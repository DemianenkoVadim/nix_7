package ua.com.alevel.controller;

import ua.com.alevel.entity.FinalRegistrationList;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;
import ua.com.alevel.service.FinalRegistrationListService;
import ua.com.alevel.service.FootballPlayerService;
import ua.com.alevel.service.FootballTeamService;
import ua.com.alevel.service.requests.RequestFootballPlayerInformation;
import ua.com.alevel.service.requests.RequestFootballTeamInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;
import static ua.com.alevel.controller.UserInterfaceController.requestsForSelection;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class FinalRegistrationListController {

    private static final FinalRegistrationListService registrationFinalList = new FinalRegistrationListService();
    private static final FootballPlayerService footballPlayer = new FootballPlayerService();
    private static final FootballTeamService footballTeam = new FootballTeamService();
    private static final NonExistentIDException idException = new NonExistentIDException();
    private static final EmptyRegistrationBaseException baseException = new EmptyRegistrationBaseException();
    private static final RequestFootballPlayerInformation requestFootballPlayerData = new RequestFootballPlayerInformation();
    private static final RequestFootballTeamInformation requestFootballTeamData = new RequestFootballTeamInformation();

    public void startFinalRegistration() throws Exception {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(in));
        String choicePoint;
        try {
            runNavigationOfRegistration();
            while ((choicePoint = inputReader.readLine()) != null) {
                launchCrudMethodsForFootballTeam(choicePoint, inputReader);
                choicePoint = inputReader.readLine();
                if (choicePoint.equals(ZERO_POSITION)) {
                    new UserInterfaceController().startsTheProgram();
                }
                launchCrudMethodsForFootballTeam(choicePoint, inputReader);
            }
        } catch (IOException wrongUserInput) {
            out.println(wrongUserInput.getMessage());
            out.println();
        }
    }

    private void runNavigationOfRegistration() {
        out.println(NAVIGATOR_FINAL_REGISTRATION);
        out.println(DO_CHOOSE);
        printsNavigator(CREATE_REGISTRATION, UPDATE_REGISTRATION, DELETE_REGISTRATION, FIND_BY_ID_REGISTRATION, FIND_ALL_FINAL_REGISTRATION,
                FIND_ALL_FOOTBALL_PLAYERS_BY_TEAM_ID_IN_REGISTRATION, FIND_ALL_TEAMS_BY_FOOTBALL_PLAYERS_ID_IN_REGISTRATION);
        out.println(EXIT_OUT_TO_PREVIOUS_MENU);
        out.println();
    }

    static void printsNavigator(String createRegistration, String updateRegistration, String deleteRegistration, String findByIdRegistration,
                                String findAllFinalRegistration, String findAllFootballPlayersByTeamIdInRegistration, String findAllTeamsByFootballPlayersIdInRegistration) {
        requestsForSelection(createRegistration, updateRegistration, deleteRegistration, findByIdRegistration, findAllFinalRegistration, findAllFootballPlayersByTeamIdInRegistration, findAllTeamsByFootballPlayersIdInRegistration);
    }

    private void launchCrudMethodsForFootballTeam(String userChoicePosition, BufferedReader readerFromConsole) throws Exception {
        switch (userChoicePosition) {
            case USER_CHOOSE_FIRST_POINT:
                createRegistration(readerFromConsole);
                break;
            case USER_CHOOSE_SECOND_POINT:
                updateRegistration(readerFromConsole);
                break;
            case USER_CHOOSE_THIRD_POINT:
                deleteRegistration(readerFromConsole);
                break;
            case USER_CHOOSE_FOURS_POINT:
                findByIdAndPrintsRegistration(readerFromConsole);
                break;
            case USER_CHOOSE_FIVES_POINT:
                findAllRegistration();
                break;
            case USER_CHOOSE_SIX_POINT:
                findAllFootballPlayersByTeamId(readerFromConsole);
                break;
            case USER_CHOOSE_SEVENS_POINT:
                findAllTeamsByFootballPlayersId(readerFromConsole);
                break;
            case USER_CHOOSE_ZERO_POINT_TEN:
                new UserInterfaceController().startsTheProgram();
                break;
        }
        runNavigationOfRegistration();
    }

    public void createRegistration(BufferedReader readerFromConsole) throws Exception {
        out.println(BAR_CREATE_FINAL_REGISTRATION);
        try {
            out.println(BASE_OF_FOOTBALL_TEAMS);
            new FootballTeamController().findAllFootballsTeam();
            out.println(BASE_OF_FOOTBALL_PLAYERS);
            new FootballPlayerController().findAllFootballPlayer();
            FinalRegistrationList registrationList = new FinalRegistrationList();
            registrationList.setFootballPlayerID(requestFootballPlayerData.requestsFootballPlayerID(readerFromConsole));
            registrationList.setFootballTeamID(requestFootballTeamData.requestsFootballTeamID(readerFromConsole));
            registrationFinalList.createFinalRegistrationList(registrationList);
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
            out.println();
        }
    }

    private void updateRegistration(BufferedReader readerFromConsole) throws Exception {
        out.println(BAR_UPDATE_FINAL_REGISTRATION);
        try {
            out.println(BASE_OF_FINAL_REGISTRATION);
            findAllRegistration();
            FinalRegistrationList registrationList = new FinalRegistrationList();
            registrationList.setFinalRegistrationListID(requestsRegistrationID(readerFromConsole));
            registrationList.setFootballPlayerID(requestFootballPlayerData.requestsFootballPlayerID(readerFromConsole));
            registrationList.setFootballTeamID(requestFootballTeamData.requestsFootballTeamID(readerFromConsole));
            registrationFinalList.updateFinalRegistrationList(registrationList);
        } catch (IOException wrongUserInput) {
            out.println(wrongUserInput.getMessage());
            out.println();
        } catch (NonExistentIDException wrongRegistrationID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongRegistrationID);
        }
        startFinalRegistration();
    }

    private void deleteRegistration(BufferedReader readerFromConsole) {
        out.println(BAR_DELETE_FINAL_REGISTRATION);
        try {
            registrationFinalList.findRegistrationById(requestsRegistrationID(readerFromConsole));
            registrationFinalList.deletePositionInFinalRegistrationList(requestsRegistrationID(readerFromConsole));
            out.println();
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
        } catch (NonExistentIDException wrongRegistrationID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongRegistrationID);
        }
    }

    private void findByIdAndPrintsRegistration(BufferedReader readerFromConsole) {
        out.println(BAR_FIND_BY_ID_FINAL_REGISTRATION);
        try {
            FinalRegistrationList listRegistration = registrationFinalList.findRegistrationById(requestsRegistrationID(readerFromConsole));
            out.println(REGISTRATION + listRegistration);
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
            out.println();
        } catch (NonExistentIDException wrongRegistrationID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongRegistrationID);
        }
    }

    private void findAllFootballPlayersByTeamId(BufferedReader readerFromConsole) {
        out.println(BAR_FIND_BY_ID_TEAM_ID_ALL_FOOTBALL_PLAYERS_FINAL_REGISTRATION);
        try {
            out.println(BASE_OF_FINAL_REGISTRATION);
            footballTeam.findAllFootballTeamsFromRegistrationBase();
            registrationFinalList.showsAllFootballPlayersInChosenByIdFootballTeam(requestFootballTeamData.requestsFootballTeamID(readerFromConsole));
        } catch (NonExistentIDException wrongRegistrationID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongRegistrationID);
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
        }
    }

    private void findAllTeamsByFootballPlayersId(BufferedReader readerFromConsole) {
        out.println(BAR_FIND_BY_ID_PLAYER_ID_ALL_FOOTBALL_TEAM_FINAL_REGISTRATION);
        try {
            out.println(BASE_OF_FINAL_REGISTRATION);
            footballPlayer.findAllFootballPlayersFromRegistrationBase();
            registrationFinalList.showsAllFootballTeamsInChosenByIdFootballPlayer(requestFootballPlayerData.requestsFootballPlayerID(readerFromConsole));
        } catch (NonExistentIDException wrongRegistrationID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongRegistrationID);
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
        }
    }

    private void findAllRegistration() throws Exception {
        try {
            registrationFinalList.readAllRegistrationList();
            out.println();
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
            startFinalRegistration();
        } catch (NonExistentIDException wrongRegistrationID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongRegistrationID);
            startFinalRegistration();
        }
    }

    public String requestsRegistrationID(BufferedReader readerFromConsole) throws IOException {
        out.println(INPUT_ID_REGISTRATION);
        return readerFromConsole.readLine();
    }
}
