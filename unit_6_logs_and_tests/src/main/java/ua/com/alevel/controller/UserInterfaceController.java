package ua.com.alevel.controller;

import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class UserInterfaceController {

    FootballPlayerController playerController = new FootballPlayerController();
    FootballTeamController teamController = new FootballTeamController();
    FinalRegistrationListController registrationListController = new FinalRegistrationListController();
    private static final NonExistentIDException idException = new NonExistentIDException();
    private static final EmptyRegistrationBaseException baseException = new EmptyRegistrationBaseException();

    public void startsTheProgram() throws Exception {
        BufferedReader readerFromConsole = new BufferedReader(new InputStreamReader(in));
        out.println(INTRODUCE);
        out.println(DESCRIPTION_FOR_THE_FOOTBALL_TEAM);
        out.println(DESCRIPTION_FOR_THE_FOOTBALL_PLAYER);
        out.println(DO_CHOOSE);
        out.println();
        String userChoice;
        try {
            runNavigationBar();
            while ((userChoice = readerFromConsole.readLine()) != null) {
                runNecessaryCrudOperations(userChoice);
                userChoice = readerFromConsole.readLine();
                if (userChoice.equals(ZERO_POSITION)) {
                    exit(CORRECT_EXIT_PROGRAM);
                }
                runNecessaryCrudOperations(userChoice);
            }
        } catch (EmptyRegistrationBaseException emptyBase) {
            baseException.printErrorEmptyRegistrationBaseExceptionMessage(emptyBase);
            out.println();
        } catch (NonExistentIDException wrongRegistrationID) {
            idException.printErrorNonExistentIDExceptionMessage(wrongRegistrationID);
            out.println();
        } catch (IOException wrongUserInput) {
            err.println(wrongUserInput.getMessage());
            out.println();
        }
    }

    private void runNavigationBar() {
        requestsForSelection(BAR, REGISTRATION_FOOTBALL_TEAM, REGISTRATION_FOOTBALL_PLAYER, LIST_FOOTBALL_TEAMS,
                LIST_FOOTBALL_PLAYERS, MAKE_REGISTRATION, EXIT_PROGRAM);
        out.println();
    }

    protected static void printsNavigator(String introduce, String createFootballTeam, String updateFootballTeam,
                                          String deleteFootballTeam, String findByIdFootballTeam,
                                          String findAllFootballTeam, String exitOutOfProgram) {
        FinalRegistrationListController.printsNavigator(introduce, createFootballTeam, updateFootballTeam, deleteFootballTeam,
                findByIdFootballTeam, findAllFootballTeam, exitOutOfProgram);
        out.println();
    }

    private void runNecessaryCrudOperations(String userChoicePosition) throws Exception {
        switch (userChoicePosition) {
            case USER_CHOOSE_FIRST_POINT:
                teamController.startFootballTeam();
                break;
            case USER_CHOOSE_SECOND_POINT:
                playerController.startsFootballPlayer();
                break;
            case USER_CHOOSE_THIRD_POINT:
                teamController.findAllFootballsTeam();
                runNavigationBar();
                break;
            case USER_CHOOSE_FOURS_POINT:
                playerController.findAllFootballPlayer();
                runNavigationBar();
                break;
            case USER_CHOOSE_FIVES_POINT:
                registrationListController.startFinalRegistration();
                break;
            case USER_CHOOSE_ZERO_POINT:
                exit(CORRECT_EXIT_PROGRAM);
                break;
        }
    }

    static void requestsForSelection(String bar, String registrationFootballTeam, String registrationFootballPlayer,
                                     String listFootballTeams, String listFootballPlayers, String makeRegistration, String exitProgram) {
        out.println(bar);
        out.println(registrationFootballTeam);
        out.println(registrationFootballPlayer);
        out.println(listFootballTeams);
        out.println(listFootballPlayers);
        out.println(makeRegistration);
        out.println(exitProgram);
    }
}
