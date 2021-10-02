package jsonparserlibrary;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static jsonparserlibrary.util.Constants.*;

public class JsonWriter {

    public static <T> void writeSourceInformationFile(List<T> lineInformation, String fileName) {
        StringBuilder lineBuilder = new StringBuilder(OPENED_CURL_BRACKET_TO_NEW_LINE);
        for (T object : lineInformation) {
            if (lineInformation.indexOf(object) != FIRST_INDEX) {
                lineBuilder.append(COMMA_T0_NEXT_NEW_LINE);
            }
            try {
                lineBuilder.append(getsInformationToJson(object));
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchFieldException e) {
                System.out.println(FILE_IS_NOT_EXIST);
            }
        }
        lineBuilder.append(CLOSE_CURLY_BRACKET);
        createsWriteInformationInFile(fileName, lineBuilder.toString());
    }

    public static <T> void writeSourceInformationFile(T[] lineInformation, String fileName) {
        StringBuilder lineBuilder = new StringBuilder(OPENED_CURL_BRACKET_TO_NEW_LINE);
        for (T object : lineInformation) {
            if (lineInformation[FIRST_INDEX] != object) {
                lineBuilder.append(COMMA_T0_NEXT_NEW_LINE);
            }
            try {
                lineBuilder.append(getsInformationToJson(object));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                System.out.println(e.getMessage());
            }
        }
        lineBuilder.append(CLOSED_CURL_BRACKET_TO_NEW_LINE);
        createsWriteInformationInFile(fileName, lineBuilder.toString());
    }

    private static void createsWriteInformationInFile(String fileName, String lineInformation) {
        try {
            if (Files.notExists(Path.of(NAME_DIRECTORY))) {
                Files.createDirectories(Path.of(NAME_DIRECTORY));
            }
            String path = PATH_DIRECTORY + fileName;
            if (Files.notExists(Path.of(path))) {
                Files.createFile(Path.of(path));
            }
            Files.writeString(Paths.get(path), lineInformation);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getsInformationToJson(Object file) throws IllegalAccessException, NoSuchFieldException {
        StringBuilder lineBuilder = new StringBuilder(EMPTY_STRING);
        if (file.getClass().isPrimitive()) {
            lineBuilder.append(getStringFromCharacter(file));
        } else if (file.getClass().isArray()) {
            lineBuilder.append(getsLineInformationFromArray(file));
        } else {
            lineBuilder.append(SLASH).append(file.getClass().getSimpleName()).append(SLASH_COMMA);
            lineBuilder.append(OPENED_CURL_BRACKET_TO_NEW_LINE);
            Field[] margin = file.getClass().getDeclaredFields();
            for (int marginArea = FIRST_INDEX; marginArea < margin.length; marginArea++) {
                if (marginArea != FIRST_INDEX) lineBuilder.append(COMMA_T0_NEXT_NEW_LINE);
                lineBuilder.append(SLASH).append(margin[marginArea].getName()).append(SLASH_COMMA);
                if (!margin[marginArea].isAccessible()) {
                    margin[marginArea].setAccessible(true);
                }
                lineBuilder.append(readValue(margin[marginArea], file));
            }
            lineBuilder.append(CLOSED_CURL_BRACKET_TO_NEW_LINE);
        }
        return lineBuilder.toString();
    }

    private static String readValue(Field stage, Object file) throws IllegalAccessException {
        String lineInformation;
        Class<?> fieldType = stage.getType();

        if (fieldType.getSimpleName().equals(BOOLEAN)) {
            if (stage.getBoolean(file)) {
                lineInformation = TRUE;
            } else lineInformation = FALSE;
        } else if (fieldType.getSimpleName().equals(STRING)) {
            lineInformation = SLASH + stage.get(file) + SLASH;
        } else if (fieldType.isArray()) {
            lineInformation = Arrays.deepToString(new Object[]{stage.get(file)})
                    .replaceAll(REGEX_EXCRETION_SLASH, OPEN_SQUARE_BRACKET).replaceAll(REGEX_EXCRETION_CLOSED_BRACKET, CLOSE_SQUARE_BRACKET);
        } else {
            long aLong = stage.getLong(file);
            lineInformation = String.valueOf(aLong);
        }
        return lineInformation;
    }

    private static String getStringFromCharacter(Object file) throws NoSuchFieldException, IllegalAccessException {
        Field field = file.getClass().getField(file.getClass().getSimpleName());
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return SLASH + file.getClass().getSimpleName() + SLASH_COMMA_SLASH + readValue(field, file) + SLASH;
    }

    private static String getsLineInformationFromArray(Object file) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder line = new StringBuilder(SLASH + file.getClass().getSimpleName() + SLASH_COMMA);
        line.append(OPENED_SQUARE_BRACKET_TO_NEW_LINE);
        int length = Array.getLength(file);
        for (int charactersInLine = FIRST_INDEX; charactersInLine < length; charactersInLine++) {
            Object objectFromArray = Array.get(file, charactersInLine);
            if (objectFromArray != null) {
                if (charactersInLine != ZERO_POINT) line.append(COMMA_T0_NEXT_NEW_LINE);
                line.append(getsInformationToJson(objectFromArray));
            }
        }
        line.append(CLOSED_SQUARE_BRACKET_TO_NEW_LINE);
        return line.toString();
    }
}
