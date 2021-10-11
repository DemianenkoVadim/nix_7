package ua.com.alevel.mapper;

import ua.com.alevel.util.DataCsvFile;

import java.util.List;

public interface CsvFileMapper {

    <T> List<T> mappingCsvFiles(Class<T> sourceFile, DataCsvFile csvInformation);
}
