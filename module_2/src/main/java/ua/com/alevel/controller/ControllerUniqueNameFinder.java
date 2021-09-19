package ua.com.alevel.controller;

import java.util.Scanner;

import static ua.com.alevel.uniquename.UniqueNameFinder.inputsUserDataInformationInConsole;
import static ua.com.alevel.uniquename.UniqueNameFinder.loadsInformationFromFile;
import static ua.com.alevel.util.Constants.USER_CHOOSE_FIRST_POINT;
import static ua.com.alevel.util.Constants.USER_CHOOSE_SECOND_POINT;
import static ua.com.alevel.util.RequestAndMessageInformationModuleTheSecond.printsIntroduceMessageUniqueNameFinder;
import static ua.com.alevel.util.RequestAndMessageInformationModuleTheSecond.printsRequestActonToDoInUniqueFinderApp;

public class ControllerUniqueNameFinder {

    private static final Scanner inputConsole = new Scanner(System.in);

    public static void dataFinderMenu() {
        printsIntroduceMessageUniqueNameFinder();
        boolean flag = true;
        while (flag) {
            printsRequestActonToDoInUniqueFinderApp();
            switch (inputConsole.nextLine()) {
                case USER_CHOOSE_FIRST_POINT:
                    loadsInformationFromFile();
                    break;
                case USER_CHOOSE_SECOND_POINT:
                    inputsUserDataInformationInConsole();
                    break;
                default: {
                    flag = false;
                }
            }
        }
    }
}
