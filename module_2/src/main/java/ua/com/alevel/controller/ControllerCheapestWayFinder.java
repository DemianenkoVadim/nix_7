package ua.com.alevel.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static ua.com.alevel.profitableway.CheapestWayFinder.findsWaysGraph;
import static ua.com.alevel.util.RequestAndMessageInformationModuleTheSecond.*;

public class ControllerCheapestWayFinder {

    private static final String inputFilePath = "module_2/src/main/resources/files/input.txt";

    public static void dataCheapestWayFinder() {
        printsIntroduceMessageCheapestWayFinder();
        Path filePath = Paths.get(inputFilePath);
        try {
            printsMessageInputInformation();
            ArrayList<String> graphStringFromFile = (ArrayList<String>) Files.readAllLines(filePath);
            graphStringFromFile.forEach(System.out::println);
            System.out.println();
            printsMessageOutputInformation();
            findsWaysGraph(graphStringFromFile);
        } catch (IOException wrongInputOutput) {
            wrongInputOutput.printStackTrace();
        }
    }
}
