package ua.com.alevel.task_1;

import ua.com.alevel.user_interfase.UserInterface;

import java.util.Scanner;

public class ConsoleSummationOfNumbers {
    private static final String YES = "Y";
    private static final String NO = "N";
    private static final String DESCRIPTION_PROGRAM = "Welcome to Task that sums all the numbers in a input String";
    private static final String ACTION_ONE = "Enter your text, it must contain numbers, otherwise the sum will be zero ";
    private static final String INCORRECT_INPUT = "WRONG INPUT! Check and correct it!";
    private static final String CHOOSE_CONTINUE = "Do you want continue TASK ? If YES press 'Y', if NO press 'N'";
    private static final String ANSWER = "The sum of the entered line is equal to: ";

    public void startingTask() {
        Scanner consoleScan = outputsData();
        System.out.println(DESCRIPTION_PROGRAM);
        System.out.println(ACTION_ONE);
        sumsNumbers(consoleScan);
    }

    private Scanner outputsData() {
        return new Scanner(System.in);
    }

    private void sumsNumbers(Scanner consoleScanning) {
        String consoleEnter = consoleScanning.nextLine();
        int total = new SummationOfNumbers().sumsNumbersTextLine(consoleEnter);
        System.out.println(ANSWER + total);
        startTaskOnceAgain();
    }

    private void startTaskOnceAgain() {
        System.out.println(CHOOSE_CONTINUE);
        Scanner chooseInputData = outputsData();
        String userChoose = chooseInputData.next();
        providesToContinueProgram(userChoose);
    }

    private void providesToContinueProgram(String choose) {
        if (YES.equalsIgnoreCase(choose)) {
            startingTask();
        } else if (NO.equalsIgnoreCase(choose)) {
            new UserInterface().startUnitOnceAgain();
        } else {
            System.err.println(INCORRECT_INPUT);
            startTaskOnceAgain();
            startingTask();
        }
    }
}
