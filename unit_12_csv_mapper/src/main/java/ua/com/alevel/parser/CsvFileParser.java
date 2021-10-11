package ua.com.alevel.parser;

import ua.com.alevel.util.DataCsvFile;

import java.io.BufferedReader;

public interface CsvFileParser {

    DataCsvFile parsing(BufferedReader streamReader);
}
