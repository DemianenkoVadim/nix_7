package ua.com.alevel.parser.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.parser.CsvFileParser;
import ua.com.alevel.util.DataCsvFile;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ua.com.alevel.util.ApplicationConstants.*;

public class CsvFileParserImpl implements CsvFileParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileParserImpl.class);

    @Override
    public DataCsvFile parsing(BufferedReader streamReader) {
        LOGGER.info(MAPPING_CSV_FILE);
        List<String[]> tableRows = new ArrayList<>();
        Scanner scanner = new Scanner(streamReader);
        while (scanner.hasNext()) {
            tableRows.add(scanner.nextLine().split(COMMA_REGEX));
        }
        return new DataCsvFile(tableRows);
    }
}
