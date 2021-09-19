package ua.com.alevel.controller;

import ua.com.alevel.dataconverter.DataConverter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static ua.com.alevel.util.Constants.*;
import static ua.com.alevel.util.RequestAndMessageInformationModuleTheSecond.*;

public class ControllerDataConverter {

    private static final String FILE = "module_2/src/main/resources/files/dates.txt";
    private static final Scanner inputConsole = new Scanner(System.in);

    public static void dataConverterMenu() {
        boolean flag = true;
        printsIntroduceMessageDataConverter();
        while (flag) {
            printsRequestActonToDoInDataConverterApp();
            String userInputChoose = inputConsole.nextLine();
            switch (userInputChoose) {
                case USER_CHOOSE_FIRST_POINT:
                    Path path = Paths.get(FILE);
                    try {
                        List<String> dateWithDelimiters = Files.readAllLines(path);
                        dateWithDelimiters.forEach(System.out::println);
                        printsInformationListFormattedData();
                        String[] formattedDates = DataConverter.checkDateToString(dateWithDelimiters);
                        for (String dateWithoutDelimiters : formattedDates) {
                            if (!dateWithoutDelimiters.equals(EMPTY_STRING)) {
                                System.out.println(dateWithoutDelimiters);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case USER_CHOOSE_SECOND_POINT:
                    try {
                        List<String> dateWithDelimiters;
                        printsInformationFormatDataInput();
                        dateWithDelimiters = List.of(inputConsole.nextLine().split(SPACE));
                        String[] formattedDates = DataConverter.checkDateToString(dateWithDelimiters);
                        for (String dateWithoutDelimiters : formattedDates) {
                            if (!dateWithoutDelimiters.equals(EMPTY_STRING)) {
                                System.out.println(dateWithoutDelimiters);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    flag = false;
            }
        }
    }
}
