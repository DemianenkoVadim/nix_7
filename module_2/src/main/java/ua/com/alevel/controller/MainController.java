package ua.com.alevel.controller;

import java.util.Scanner;

import static ua.com.alevel.util.Constants.*;
import static ua.com.alevel.util.RequestAndMessageInformationModuleTheSecond.printsMessageMenuInformation;
import static ua.com.alevel.util.RequestAndMessageInformationModuleTheSecond.printsRequestMainMenu;

public class MainController {

    private static final Scanner consoleInput = new Scanner(System.in);

    public void printsMessageMenuInformationAndRequestUserChoice() {
        boolean flag = true;
        while (flag) {
            printsMessageMenuInformation();
            printsRequestMainMenu();
            String userChoiceToDo = consoleInput.nextLine();
            switch (userChoiceToDo) {
                case USER_CHOOSE_FIRST_POINT:
                    ControllerDataConverter.dataConverterMenu();
                    break;
                case USER_CHOOSE_SECOND_POINT:
                    ControllerUniqueNameFinder.dataFinderMenu();
                    break;
                case USER_CHOOSE_THIRD_POINT:
                    ControllerCheapestWayFinder.dataCheapestWayFinder();
                    break;
                default:
                    flag = false;
            }
        }
    }
}
