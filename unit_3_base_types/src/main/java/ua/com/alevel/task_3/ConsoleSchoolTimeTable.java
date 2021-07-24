package ua.com.alevel.task_3;

import ua.com.alevel.user_interfase.UserInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

import static ua.com.alevel.task_3.Flag.IS_VALIDATE;

public class ConsoleSchoolTimeTable {
    private static final int ZERO_LESSON = 0;
    private static final int LAST_LESSON = 11;
    private static final String YES = "Y";
    private static final String NO = "N";
    private static final String INCORRECT_INPUT = "WRONG INPUT! Check and correct it!";
    private static final String START_PROGRAM = "Welcome to Task 'School Time Table'";
    private static final String FIRST_ACTION = "Enter the lesson you are interested in (from 1 to 10) and you will find out the time of it's end: ";
    private static final String WRONG_INPUT_LESSON = "Please input right number of lesson, from 1 to 10: ";
    private static final String WRONG_INPUT_NOT_NUMBER = "You input not a number.Try again!";
    private static final String CHOOSE_CONTINUE = "Do you want continue Task? If YES press 'Y', if NO press 'N'";

    public void startsProgram() {
        Scanner consoleScan = outputsData();
        System.out.println(START_PROGRAM + "\n" + FIRST_ACTION);
        checksFirstlyInputData(consoleScan);
    }

    private Scanner outputsData() {
        return new Scanner(System.in);
    }

    private void checksFirstlyInputData(Scanner consoleScanning) {
        do {
            IS_VALIDATE.setState(true);
            try {
                int consoleInput = consoleScanning.nextInt();
                consoleInput = Integer.parseInt(String.valueOf(consoleInput));
                if (consoleInput <= ZERO_LESSON || consoleInput >= LAST_LESSON) {
                    IS_VALIDATE.setState(true);
                    System.out.println(WRONG_INPUT_LESSON);
                } else {
                    System.out.println(new SchoolTimeTable().countsTheEndOfLessons(consoleInput));
                    System.out.println(CHOOSE_CONTINUE);
                    startsProgramSecondTime();
                }
            } catch (InputMismatchException e) {
                IS_VALIDATE.setState(true);
                System.err.println(WRONG_INPUT_NOT_NUMBER);
                System.out.println(CHOOSE_CONTINUE);
                startsProgramSecondTime();
            }
        } while (IS_VALIDATE.getState());
    }

    private void startsProgramSecondTime() {
        Scanner console = outputsData();
        checksSecondlyInputData(console);
    }

    private void checksSecondlyInputData(Scanner inputData) {
        try {
            providesOptionToContinueProgram(inputData);
        } catch (InputMismatchException e) {
            System.err.println(INCORRECT_INPUT);
            startsProgramSecondTime();
            System.out.println(CHOOSE_CONTINUE);
        }
    }

    private void providesOptionToContinueProgram(Scanner chooseInputData) {
        String user_Choose = chooseInputData.next();
        if (YES.equalsIgnoreCase(user_Choose)) {
            startsProgram();
        } else if (NO.equalsIgnoreCase(user_Choose)) {
            new UserInterface().startUnitOnceAgain();
        } else {
            System.err.println(INCORRECT_INPUT);
            System.out.println(CHOOSE_CONTINUE);
            startsProgramSecondTime();
        }
    }
}
