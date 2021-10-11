package ua.com.alevel.parser.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static ua.com.alevel.util.ConstantsApplication.*;

public class ParserImpl implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(LOGGER_INFO);

    @Override
    public Properties getsProperties(String filePath) {
        LOGGER.info(PROGRAM_PROPERTIES);
        Properties applicationCharacteristic = new Properties();
        try (BufferedReader inputFile = new BufferedReader(new FileReader(filePath))) {
            applicationCharacteristic.load(inputFile);
        } catch (IOException fileNotFound) {
            LOGGER.info(MESSAGE_ERROR + fileNotFound);
        }
        return applicationCharacteristic;
    }
}
