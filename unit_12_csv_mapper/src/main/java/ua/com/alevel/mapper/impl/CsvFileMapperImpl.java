package ua.com.alevel.mapper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.annotations.MapperCsvFile;
import ua.com.alevel.mapper.CsvFileMapper;
import ua.com.alevel.util.DataCsvFile;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.time.LocalDateTime.parse;
import static ua.com.alevel.util.ApplicationConstants.*;

public class CsvFileMapperImpl implements CsvFileMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileMapperImpl.class);
    private static final DateTimeFormatter dataInformationFormatter = DateTimeFormatter.ofPattern(DATA_PATTERN_YEAR_MONTH_DAY_HOUR_MINUTES);

    @Override
    public <T> List<T> mappingCsvFiles(Class<T> sourceFile, DataCsvFile csvInformation) {
        LOGGER.info(MAPPING_CSV_FILE);
        List<T> mappingResults = new ArrayList<>();
        Constructor<T> informationAboutConstructor;
        try {
            informationAboutConstructor = sourceFile.getConstructor();
        } catch (NoSuchMethodException falseMethod) {
            LOGGER.error(MESSAGE_ERROR, falseMethod);
            throw new RuntimeException(falseMethod);
        }
        try {
            for (int constructorInformation = LIST_FIRST_ELEMENT; constructorInformation < csvInformation.size(); constructorInformation++) {
                T objectiveType = informationAboutConstructor.newInstance();
                for (Field fieldInformation : objectiveType.getClass().getDeclaredFields()) {
                    fieldInformation.setAccessible(true);
                    if (fieldInformation.isAnnotationPresent(MapperCsvFile.class)) {
                        MapperCsvFile mapperCsvFile = fieldInformation.getAnnotation(MapperCsvFile.class);
                        String headerName = mapperCsvFile.value();

                        if (fieldInformation.getType() == Integer.class) {
                            fieldInformation.set(objectiveType, parseInt(csvInformation.get(constructorInformation, headerName)));

                        } else if (fieldInformation.getType() == Long.class) {
                            fieldInformation.set(objectiveType, parseLong(csvInformation.get(constructorInformation, headerName)));

                        } else if (fieldInformation.getType() == Float.class) {
                            fieldInformation.set(objectiveType, parseFloat(csvInformation.get(constructorInformation, headerName)));

                        } else if (fieldInformation.getType() == Double.class) {
                            fieldInformation.set(objectiveType, parseDouble(csvInformation.get(constructorInformation, headerName)));

                        } else if (fieldInformation.getType() == Boolean.class) {
                            fieldInformation.set(objectiveType, parseBoolean(csvInformation.get(constructorInformation, headerName)));

                        } else if (fieldInformation.getType() == LocalDateTime.class) {
                            try {
                                fieldInformation.set(objectiveType, parse(csvInformation.get(constructorInformation, headerName), dataInformationFormatter));
                            } catch (DateTimeParseException parseDataException) {
                                LOGGER.error(MESSAGE_ERROR, parseDataException);
                            }
                        } else {
                            fieldInformation.set(objectiveType, csvInformation.get(constructorInformation, headerName));
                        }
                    }
                    fieldInformation.setAccessible(false);
                }
                mappingResults.add(objectiveType);
            }
        } catch (IllegalAccessException accessException) {
            LOGGER.error(MESSAGE_ERROR, accessException);
        } catch (InvocationTargetException targetException) {
            LOGGER.error(MESSAGE_ERROR, targetException);
        } catch (InstantiationException onInstantiation) {
            LOGGER.error(MESSAGE_ERROR, onInstantiation);
        }
        return mappingResults;
    }
}
