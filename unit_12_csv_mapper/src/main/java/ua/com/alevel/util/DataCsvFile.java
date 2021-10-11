package ua.com.alevel.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static ua.com.alevel.util.ApplicationConstants.*;

public class DataCsvFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataCsvFile.class);

    private final List<String[]> rowsInformation;
    private final Map<String, Integer> columnHeaders;

    public DataCsvFile(List<String[]> tableRows) {
        this.rowsInformation = new ArrayList<>();
        this.columnHeaders = new HashMap<>();
        this.rowsInformation.addAll(tableRows);
        IntStream.range(LIST_FIRST_ELEMENT, rowsInformation.get(LIST_FIRST_ELEMENT).length).forEach(rowTable ->
                columnHeaders.put(rowsInformation.get(LIST_FIRST_ELEMENT)[rowTable], rowTable));
    }

    public int size() {
        return rowsInformation.size() - ONE_ELEMENT;
    }

    public String get(int row, int column) {
        return rowsInformation.get(row + ONE_ELEMENT)[column];
    }

    public String get(int row, String title) {
        int column = columnHeaders.get(title);
        if (column == OUT_OF_BORDER) {
            LOGGER.error(NO_FIELD_WITH_SUCH_NAME);
        }
        return rowsInformation.get(row + ONE_ELEMENT)[column];
    }

    public String[] getColumnHeaders() {
        return this.rowsInformation.get(LIST_FIRST_ELEMENT);
    }

    public void add(String[] row) {
        this.rowsInformation.add(row);
    }
}
