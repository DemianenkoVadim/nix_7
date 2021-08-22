package ua.com.alevel.controller;

import ua.com.alevel.entity.FootballPlayer;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;
import ua.com.alevel.service.FootballPlayerService;
import ua.com.alevel.service.requests.RequestFootballPlayerInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;
import static ua.com.alevel.controller.UserInterfaceController.printsNavigator;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class FootballPlayerController {

    private static final FootballPlayerService footballPlayer = new FootballPlayerService();
    private static final NonExistentIDException idException = new NonExistentIDException();
    private static final EmptyRegistrationBaseException baseException = new EmptyRegistrationBaseException();
    private static final RequestFootballPlayerInformation requestFootballPlayerData = new RequestFootballPlayerInformation();

    public void startsFootballPlayer() throws Exception {
        BufferedReader readerFromConsole = new BufferedReader(new InputStreamReader(in));
        String userChoice;
        try {
            runFootballPlayerNavigation();
            while ((userChoice = readerFromConsole.readLine()) != null) {
                launchCrudMethodForFootballPlayer(userChoice, readerFromConsole);
                userChoice = readerFromConsole.readLine();
                if (userChoice.equals(ZERO_POSITION)) {
                    new UserInterfaceController().startsTheProgram();
                }
                launchCrudMethodForFootballPlayer(userChoice, readerFromConsole);
            }
        } catch (IOException wrongUserInput) {
            out.println(wrongUserInput.getMessage());
        }
    }

    private void runFootballPlayerNavigation() {
        printsNavigator(CREATE_FOOTBALL_PLAYER, UPDATE_FOOTBALL_PLAYER, DELETE_FOOTBALL_PLAYER,
                FIND_BY_ID_FOOTBALL_PLAYER, FIND_ALL_FOOTBALL_PLAYER, EXIT_OUT_TO_PREVIOUS_MENU, DO_CHOOSE);
    }

    private void launchCrudMethodForFootballPlayer(String userChoicePosition, BufferedReader readerFromConsole) throws Exception {
        switch (userChoicePosition) {
            case USER_CHOOSE_FIRST_POINT:
                createFootballPlayer(readerFromConsole);
                break;
            case USER_CHOOSE_SECOND_POINT:
                updateFootballPlayer(readerFromConsole);
                break;
            case USER_CHOOSE_THIRD_POINT:
                deleteFootballPlayer(readerFromConsole);
                break;
            case USER_CHOOSE_FOURS_POINT:
                findAndPrintByIdFootballPlayer(readerFromConsole);
                break;
            case USER_CHOOSE_FIVES_POINT:
                findAllFootballPlayer();
                break;
            case USER_CHOOSE_ZERO_POINT_TEN:
                new UserInterfaceController().startsTheProgram();
                break;
        }
        runFootballPlayerNavigation();
    }

    void createFootballPlayer(BufferedReader readerFromConsole) throws Exception {
        out.println(BAR_CREATE_PLAYER);
        try {
            createsInfoOfFootballPlayer(readerFromConsole);
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
            out.println();
        }
    }

    private void updateFootballPlayer(BufferedReader readerFromConsole) throws Exception {
        out.println(BAR_UPDATE_PLAYER);
        try {
            footballPlayer.findFootballPlayerFromRegistrationBaseById(requestFootballPlayerData.requestsFootballPlayerID(readerFromConsole));
            createsInfoOfFootballPlayer(readerFromConsole);
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
            out.println();
        } catch (NonExistentIDException wrongPlayerID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongPlayerID);
        }
    }

    private void createsInfoOfFootballPlayer(BufferedReader readerFromConsole) throws Exception {
        FootballPlayer player = new FootballPlayer();
        player.setFirstName(requestFootballPlayerData.requestsFootballPlayerFirstName(readerFromConsole));
        player.setLastName(requestFootballPlayerData.requestsFootballPlayerLastName(readerFromConsole));
        player.setFootballPlayerPosition(requestFootballPlayerData.requestsFootballPlayerPosition(readerFromConsole));
        player.setFootballPlayerNational(requestFootballPlayerData.requestsFootballPlayerNationality(readerFromConsole));
        player.setAgeOfFootballPlayer(requestFootballPlayerData.requestsFootballPlayerAge(readerFromConsole));
        footballPlayer.createFootballPlayerInRegistrationBase(player);
        out.println(DONE);
        out.println();
        startsFootballPlayer();
    }

    private void deleteFootballPlayer(BufferedReader readerFromConsole) throws Exception {
        out.println(BAR_DELETE_PLAYER);
        try {
            footballPlayer.findAllFootballPlayersFromRegistrationBase();
            footballPlayer.deleteFootballPlayerFromRegistrationBase(requestFootballPlayerData.requestsFootballPlayerID(readerFromConsole));
            out.println();
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
        } catch (NonExistentIDException wrongPlayerID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongPlayerID);
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
            out.println();
        }
    }

    private void findAndPrintByIdFootballPlayer(BufferedReader readerFromConsole) {
        out.println(BAR_FIND_BY_ID_PLAYER);
        try {
            FootballPlayer player = footballPlayer.findFootballPlayerFromRegistrationBaseById(requestFootballPlayerData.requestsFootballPlayerID(readerFromConsole));
            out.println(FOOTBALL_PLAYER + player);
        } catch (NonExistentIDException wrongPlayerId) {
            idException.printErrorNonExistentIDExceptionMessage(wrongPlayerId);
        }
    }

    protected void findAllFootballPlayer() throws Exception {
        try {
            footballPlayer.findAllFootballPlayersFromRegistrationBase();
            out.println();
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
        }
    }
}
