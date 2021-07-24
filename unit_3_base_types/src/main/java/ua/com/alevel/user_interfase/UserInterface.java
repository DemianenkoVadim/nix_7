package ua.com.alevel.user_interfase;

import ua.com.alevel.task_1.ConsoleSummationOfNumbers;
import ua.com.alevel.task_2.ConsoleSortingLetters;
import ua.com.alevel.task_3.ConsoleSchoolTimeTable;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private static final int CHOOSE_FIRST_PROGRAM = 1;
    private static final int CHOOSE_SECOND_PROGRAM = 2;
    private static final int CHOOSE_THIRD_PROGRAM = 3;
    private static final int CHOOSE_EXIT_PROGRAM = 0;
    private static final String YES = "Y";
    private static final String NO = "N";
    private static final String WELCOME_TO_THE_PROGRAM = "Welcome to unit_3!";
    private static final String DESCRIPTION_OF_UNIT_3 = "If you want to run the Task which: " + "\n" +
            "1) sums all the numbers in the entered line, clicking - '1' " + "\n" +
            "2) counts the number of incoming letters (Latin / Cyrillic) in the entered line and sort them by, clicking - '2' " + "\n" +
            "3) showing the end time of the lessons, clicking - '3' ";
    private static final String CHOOSE_EXIT_THE_PROGRAM = "If you want exit the Unit clicking - 0 ";
    private static final String USER_CHOOSE = "Make your choose: ";
    private static final String WRONG_INPUT_CHOOSE = "Your input is incorrect. Be attentive!";
    private static final String CHOOSE_CONTINUE_UNIT = "Do you want continue UNIT? If YES press 'Y', if NO press 'N'";
    private static final String LEAVE_PROGRAM = "Best regrets!";

    public void startsUnit() {
        describesTheEssenceOfTheProgram();
        Scanner consoleUserChoose = supplyInputData();
        checksInputData(consoleUserChoose);
    }

    private static Scanner supplyInputData() {
        return new Scanner(System.in);
    }

    private void checksInputData(Scanner inputData) {
        try {
            int consoleInput = inputData.nextInt();
            consoleInput = Integer.parseInt(String.valueOf(consoleInput));
            choosingTask(consoleInput);
        } catch (InputMismatchException e) {
            System.err.println(WRONG_INPUT_CHOOSE);
            startsUnit();
        }
    }

    private void describesTheEssenceOfTheProgram() {
        System.out.println(WELCOME_TO_THE_PROGRAM);
        System.out.println(DESCRIPTION_OF_UNIT_3);
        System.out.println(CHOOSE_EXIT_THE_PROGRAM);
        System.out.println(USER_CHOOSE);
    }

    private void choosingTask(int userChoose) {
        if (userChoose == CHOOSE_FIRST_PROGRAM) {
            new ConsoleSummationOfNumbers().startingTask();
        } else if (userChoose == CHOOSE_SECOND_PROGRAM) {
            new ConsoleSortingLetters().startTask();
        } else if (userChoose == CHOOSE_THIRD_PROGRAM) {
            new ConsoleSchoolTimeTable().startsProgram();
        } else if (userChoose == CHOOSE_EXIT_PROGRAM) {
            System.out.println(LEAVE_PROGRAM);
            System.exit(CHOOSE_EXIT_PROGRAM);
        } else {
            System.err.println(WRONG_INPUT_CHOOSE);
            startsUnit();
        }
    }

    public void startUnitOnceAgain() {
        System.out.println(CHOOSE_CONTINUE_UNIT);
        Scanner chooseInputData = supplyInputData();
        String clientChoose = chooseInputData.next();
        proposeToContinueUnit(clientChoose);
    }

    private void proposeToContinueUnit(String selection) {
        if (YES.equalsIgnoreCase(selection)) {
            startsUnit();
        } else if (NO.equalsIgnoreCase(selection)) {
            System.out.println(LEAVE_PROGRAM);
            System.exit(CHOOSE_EXIT_PROGRAM);
        } else {
            System.err.println(WRONG_INPUT_CHOOSE);
            startUnitOnceAgain();
            startsUnit();
        }
    }
}
