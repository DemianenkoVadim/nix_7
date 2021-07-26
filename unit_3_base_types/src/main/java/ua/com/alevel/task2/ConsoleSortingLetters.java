package ua.com.alevel.task2;

import ua.com.alevel.user_interfase.UserInterface;

import java.util.Scanner;

public class ConsoleSortingLetters {

    private static final String YES = "Y";
    private static final String NO = "N";
    private static final String PROGRAM_DESCRIPTION = "Welcome to Task that counting letters in input string and than sort it";
    private static final String FIRST_PROGRAM_ACTION = "Enter your test: ";
    private static final String INCORRECT_INPUT = "WRONG INPUT! Check and correct it!";
    private static final String CHOOSE_CONTINUE = "Do you want continue TASK? If YES press 'Y', if NO press 'N'";

    public void startTask() {
        Scanner consoleInput = outputsData();
        System.out.println(PROGRAM_DESCRIPTION);
        System.out.println(FIRST_PROGRAM_ACTION);
        countAndSortLetters(consoleInput);
    }

    private Scanner outputsData() {
        return new Scanner(System.in);
    }

    private void countAndSortLetters(Scanner consoleScanning) {
        String consoleEnter = consoleScanning.nextLine();
        new SortingLetters().sortedCountEachLetterAndDisplay(consoleEnter);
        startsTaskOnceAgain();
    }

    private void startsTaskOnceAgain() {
        System.out.println(CHOOSE_CONTINUE);
        Scanner chooseInputData = outputsData();
        String clientChoose = chooseInputData.next();
        proposeToContinueTask(clientChoose);
    }

    private void proposeToContinueTask(String selection) {
        if (YES.equalsIgnoreCase(selection)) {
            startTask();
        } else if (NO.equalsIgnoreCase(selection)) {
            new UserInterface().startUnitOnceAgain();
        } else {
            System.err.println(INCORRECT_INPUT);
            startsTaskOnceAgain();
            startTask();
        }
    }
}
