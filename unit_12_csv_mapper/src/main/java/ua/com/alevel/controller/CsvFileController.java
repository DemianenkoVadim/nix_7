package ua.com.alevel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.entity.PlayerOfFootballTeam;
import ua.com.alevel.mapper.CsvFileMapper;
import ua.com.alevel.mapper.impl.CsvFileMapperImpl;
import ua.com.alevel.parser.CsvFileParser;
import ua.com.alevel.parser.impl.CsvFileParserImpl;
import ua.com.alevel.util.DataCsvFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;
import static ua.com.alevel.util.ApplicationConstants.*;
import static ua.com.alevel.util.CustomUtilRequests.*;

public class CsvFileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileController.class);
    private static final CsvFileMapper CSV_MAPPER = new CsvFileMapperImpl();
    private static final CsvFileParser CSV_PARSER = new CsvFileParserImpl();
    private static final CsvFileController instance = new CsvFileController();

    public static CsvFileController getInstance() {
        return instance;
    }

    public void startsApplication(String filePath) {
        Scanner consoleUserInput = new Scanner(System.in);

        DataCsvFile informationCsvFile;
        try (BufferedReader inputDataFile = new BufferedReader(new FileReader(filePath))) {
            informationCsvFile = CSV_PARSER.parsing(inputDataFile);
        } catch (IOException fileNotFound) {
            LOGGER.error(MESSAGE_ERROR, fileNotFound);
            throw new RuntimeException(fileNotFound);
        }

        boolean applicationIsRunning = true;
        while (applicationIsRunning) {
            String row, column;

            printsBarMenu();
            String userChoice = consoleUserInput.nextLine();

            switch (userChoice) {
                case FIRST_ACTION_USER_CHOOSE:
                    List<PlayerOfFootballTeam> footballPlayers = CSV_MAPPER.mappingCsvFiles(PlayerOfFootballTeam.class, informationCsvFile);
                    for (PlayerOfFootballTeam player : footballPlayers) {
                        out.println(player.toString());
                    }
                    break;
                case SECOND_ACTION_USER_CHOOSE:
                    printsMessageRequestToChooseARow();
                    row = consoleUserInput.nextLine();
                    printsMessageRequestToChooseAColumn();
                    column = consoleUserInput.nextLine();
                    try {
                        out.println(informationCsvFile.get(Integer.parseInt(row), Integer.parseInt(column)));
                    } catch (RuntimeException applicationExecutionError) {
                        LOGGER.error(MESSAGE_ERROR, applicationExecutionError);
                    }
                    break;
                case THIRD_ACTION_USER_CHOOSE:
                    printsMessageRequestToChooseARow();
                    row = consoleUserInput.nextLine();
                    printsMessageRequestToChooseTitleName();
                    column = consoleUserInput.nextLine();
                    try {
                        out.println(informationCsvFile.get(Integer.parseInt(row), column));
                    } catch (RuntimeException applicationExecutionError) {
                        LOGGER.error(MESSAGE_ERROR, applicationExecutionError);
                    }
                    break;
                case FOURTH_ACTION_USER_CHOOSE:
                    out.println(Arrays.toString(informationCsvFile.getColumnHeaders()));
                    break;
                default:
                    applicationIsRunning = false;
            }
        }
    }

    private void printsBarMenu() {
        for (String barMenuValues : Arrays.asList(REQUEST_TO_CHOOSE_AN_ACTION, FIRST_ACTION_IN_APPLICATION, SECOND_ACTION_IN_APPLICATION,
                THIRD_ACTION_IN_APPLICATION, FOURS_ACTION_IN_APPLICATION, EXIT_APPLICATION)) {
            printsRequestsOrInformationMessages(barMenuValues);
        }
    }
}
