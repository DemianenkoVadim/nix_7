package ua.com.alevel.mapper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.annotattions.PropertyKey;
import ua.com.alevel.mapper.Mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import static ua.com.alevel.util.ConstantsApplication.*;

public class MapperImpl implements Mapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(LOGGER_INFO);
    private static final DateFormat format = new SimpleDateFormat(DATA_PATTERN_YEAR_MONTH_DAY);

    @Override
    public <T> T mappingTheFile(Class<T> originalType, Properties singleObject) {
        LOGGER.info(PARSE_THE_OBJECT);
        T operand = null;
        try {
            operand = Objects.requireNonNull(originalType.getConstructor()).newInstance();
            for (Field informationOfField : operand.getClass().getFields()) {

                if (informationOfField.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey signature = informationOfField.getAnnotation(PropertyKey.class);
                    String valueFromPropertySignature = singleObject.getProperty(signature.value());

                    if (informationOfField.getType().equals(Integer.TYPE)) {
                        informationOfField.set(operand, Integer.parseInt(valueFromPropertySignature));
                    } else if (informationOfField.getType().equals(Long.TYPE)) {
                        informationOfField.set(operand, Long.parseLong(valueFromPropertySignature));
                    } else if (informationOfField.getType().equals(Float.TYPE)) {
                        informationOfField.set(operand, Float.parseFloat(valueFromPropertySignature));
                    } else if (informationOfField.getType().equals(Double.TYPE)) {
                        informationOfField.set(operand, Double.parseDouble(valueFromPropertySignature));
                    } else if (informationOfField.getType().equals(Boolean.TYPE)) {
                        informationOfField.set(operand, Boolean.parseBoolean(valueFromPropertySignature));
                    } else if (informationOfField.getType() == Date.class) {
                        try {
                            informationOfField.set(operand, format.parse(valueFromPropertySignature));
                        } catch (ParseException decipherError) {
                            LOGGER.info(MESSAGE_ERROR + decipherError);
                        }
                    } else {
                        informationOfField.set(operand, valueFromPropertySignature);
                    }
                }
            }
        } catch (IllegalAccessException excludeAccess) {
            LOGGER.info(MESSAGE_ERROR + excludeAccess);
        } catch (NumberFormatException formattingNumberError) {
            LOGGER.info(MESSAGE_ERROR + formattingNumberError);
        } catch (InvocationTargetException targetError) {
            LOGGER.info(MESSAGE_ERROR + targetError);
        } catch (InstantiationException objectError) {
            LOGGER.info(MESSAGE_ERROR + objectError);
        } catch (NoSuchMethodException methodError) {
            LOGGER.info(MESSAGE_ERROR + methodError);
        }
        return operand;
    }
}
