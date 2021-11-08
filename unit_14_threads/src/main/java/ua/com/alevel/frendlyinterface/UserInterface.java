package ua.com.alevel.frendlyinterface;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;
import static ua.com.alevel.primenumbers.Numbers.printCountResultPrimeNumber;
import static ua.com.alevel.primenumbers.Numbers.printsRandomGeneratedNumbers;
import static ua.com.alevel.reversethreads.ReverseThread.starter;

@Log4j2
public class UserInterface {

    public static final int CORRECT_EXIT_PROGRAM = 0;
    public static final String USER_CHOOSE_FIRST_TASK = "1";
    public static final String USER_CHOOSE_SECOND_TASK = "2";
    public static final String USER_CHOOSE_EXIT_APPLICATION = "0";

    public void startApplication() {
        String userChoice;
        System.out.println();
        System.out.println("---  WELCOME TO APPLICATION ---");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            printsNavigationMenu();
            while ((userChoice = reader.readLine()) != null) {
                switch (userChoice) {
                    case USER_CHOOSE_FIRST_TASK -> callFirstTask();
                    case USER_CHOOSE_SECOND_TASK -> callSecondTask();
                    case USER_CHOOSE_EXIT_APPLICATION -> exit(CORRECT_EXIT_PROGRAM);
                }
                startApplication();
            }
        } catch (IOException inputOutputError) {
            log.error("User input mistake " + inputOutputError.getMessage());
        }
    }

    private static void printsNavigationMenu() {
        System.out.println();
        System.out.println("If you want to WATCH A RESULT OF CALLED IN REVERSE ORDER THREADS ---------------- PRESS 1");
        System.out.println("If you want to WATCH NUMBER RANDOM SEARCHING OF PRIME DIGITS -------------------- PRESS 2");
        System.out.println("If you want to EXIT THE APPLICATION --------------------------------------------- PRESS 0");
        System.out.println();
    }

    private void callFirstTask() {
        starter();
    }

    private void callSecondTask() {
        printsRandomGeneratedNumbers();
        printCountResultPrimeNumber();
    }
}
