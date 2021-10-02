package jsonparserlibrary;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static jsonparserlibrary.util.Constants.*;

public class JsonReader {

    public static <T> List<T> readSourceInformationFile(String fileName, Object files) {
        List<T> filesResult = new ArrayList<>();
        try {
            if (Files.notExists(Path.of(NAME_DIRECTORY))) {
                return null;
            }
            String path = PATH_DIRECTORY + fileName;
            if (Files.notExists(Path.of(path))) {
                return null;
            }
            String json = Files.readString(Paths.get(path)).replaceAll(NEW_LINE, SPACE).trim();
            filesResult = getListObjectsFromString(json, files);
        } catch (ClassNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(ACCESS_ERROR);
        } catch (InvocationTargetException e) {
            System.out.println(WRONG_AUGMENT);
        } catch (NoSuchMethodException e) {
            System.out.println(WRONG_NAME_METHOD);
        } catch (IOException e) {
            System.out.println(WRONG_IO_NIO_ACCESS);
        }
        return filesResult;
    }

    private static <T> List<T> getListObjectsFromString(String json, Object files) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        int indexSeparator = json.indexOf(TWO_DOTS);
        String nameObject;
        String value;
        if (checksObject(json)) {
            nameObject = json.substring(SECOND_INDEX, indexSeparator).replaceAll(SLASH, EMPTY_STRING).trim();
            value = json.substring(indexSeparator + ONE_INDEX, json.length() - ONE_INDEX).trim();
            if (nameObject.endsWith(OPENED_CLOSED_SQUARE_BRACKETS)) {
                List<T> listObject = new ArrayList<>();
                String[] stringsObject = getArrayFromString(value, OPENED_SQUARE_BRACKET, CLOSED_SQUARE_BRACKET);
                for (String lines : stringsObject) {
                    if (lines != null)
                        listObject.addAll(Objects.requireNonNull(getListObjectsFromString(lines, files)));
                }
                return listObject;
            } else {
                List<T> objectList = new ArrayList<>();
                Class classObject = Class.forName(files.getClass().getPackageName() + DOT + nameObject);
                String[] valueArray = value.split(nameObject);
                Map<String, String> mapKeyValue;
                String[] arrays;
                for (String valAr : valueArray) {
                    String[] jsonObjects = getArrayFromString(valAr, OPENED_CURLY_BRACKET, CLOSED_CURLY_BRACKET);
                    for (String jsonOb : jsonObjects) {
                        if (jsonOb != null) {
                            T result = (T) classObject.getConstructor().newInstance();
                            arrays = getArrayFromString(jsonOb, OPENED_SQUARE_BRACKET, CLOSED_SQUARE_BRACKET);
                            mapKeyValue = getMap(jsonOb);
                            result = setsObject(mapKeyValue, result, arrays);
                            objectList.add(result);
                        }
                    }
                }
                return objectList;
            }
        }
        return null;
    }

    private static <T> T setsObject(Map<String, String> mapKeyValue, T result, String[] arrays) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] fields = result.getClass().getDeclaredFields();
        for (Map.Entry<String, String> element : mapKeyValue.entrySet()) {
            String key = element.getKey().replaceAll(SLASH, EMPTY_STRING);
            String stringValue = element.getValue().replaceAll(SLASH, EMPTY_STRING);
            for (Field field : fields) {
                String fieldName = getsStringWithFirstLetterToUpperCaseLetter(field.getName());
                if (field.getName().equals(key)) {
                    Method method = result.getClass().getMethod(SET + fieldName, field.getType());
                    if (field.getType().getSimpleName().equals(STRING)) {
                        method.invoke(result, stringValue);
                    } else if (field.getType().getSimpleName().equals(BOOLEAN)) {
                        if (stringValue.equals(NULL)) method.invoke(result, false);
                        else method.invoke(result, Boolean.parseBoolean(stringValue));
                    } else if (field.getType().getSimpleName().endsWith(OPENED_CLOSED_SQUARE_BRACKETS)) {
                        String array = arrays[FIRST_INDEX].trim();
                        String[] temp = array.split(COMMA);
                        int[] indexArray = new int[temp.length];
                        for (int index = FIRST_INDEX; index < temp.length; index++) {
                            if (temp[index].equals(NULL)) indexArray[index] = ZERO_POINT;
                            else indexArray[index] = Integer.parseInt(temp[index]);
                        }
                        method.invoke(result, indexArray);
                    } else {
                        if (stringValue.equals(NULL)) method.invoke(result, FIRST_INDEX);
                        else method.invoke(result, Integer.parseInt(stringValue));
                    }
                }
            }
        }
        return result;
    }

    private static Map<String, String> getMap(String value) {
        Map<String, String> map = new HashMap<>();
        String[] keyValue = value.split(COMMA);
        for (String keyVal : keyValue) {
            if (keyVal.contains(TWO_DOTS)) {
                int indexSeparator = keyVal.indexOf(TWO_DOTS);
                String key = keyVal.substring(FIRST_INDEX, indexSeparator);
                if (indexSeparator < keyVal.length()) {
                    String mapValue = keyVal.substring(indexSeparator + SECOND_INDEX);
                    map.put(key, mapValue);
                } else map.put(key, NULL);
            }
        }
        return map;
    }

    private static boolean checksObject(String json) {
        return json.startsWith(OPEN_CURLY_BRACKET);
    }

    private static String[] getArrayFromString(String line, char openedBracket, char closedBracket) {
        String[] objects = new String[countsBrackets(line, openedBracket, closedBracket)];
        for (int sign = FIRST_INDEX; sign < objects.length; sign++) {
            String first = getsOneTypeOfBrackets(line, openedBracket, closedBracket);
            if (first != null) {
                String second = line.replaceFirst(first, EMPTY_STRING);
                objects[sign] = first.trim();
                line = second;
            } else break;
        }
        return objects;
    }

    private static String getsOneTypeOfBrackets(String line, char openedBracket, char closedBracket) {
        char[] bracketSymbol = line.toCharArray();
        for (int sign = FIRST_INDEX; sign < bracketSymbol.length; sign++) {
            if (bracketSymbol[sign] == openedBracket) {
                for (int nextSign = sign; nextSign < bracketSymbol.length; nextSign++) {
                    if (bracketSymbol[nextSign] == closedBracket) {
                        bracketSymbol[sign] = SPACE_CHAR;
                        bracketSymbol[nextSign] = SPACE_CHAR;
                        StringBuilder builder = new StringBuilder(EMPTY_STRING);
                        for (int note = sign; note < nextSign; note++) {
                            builder.append(bracketSymbol[note]);
                        }
                        if (findsAllBrackets(builder.toString()))
                            return builder.toString();
                        else {
                            bracketSymbol[sign] = openedBracket;
                            bracketSymbol[nextSign] = closedBracket;
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }

    private static String getsStringWithFirstLetterToUpperCaseLetter(String nameLine) {
        String firstLetter = nameLine.substring(FIRST_INDEX, SECOND_INDEX).toUpperCase();
        String theEndOfTheWord = nameLine.substring(SECOND_INDEX);
        return firstLetter + theEndOfTheWord;
    }

    private static boolean findsAllBrackets(String line) {
        return findsTypesBrackets(line, OPENED_CURLY_BRACKET, CLOSED_CURLY_BRACKET)
                && findsTypesBrackets(line, OPENED_SQUARE_BRACKET, CLOSED_SQUARE_BRACKET);
    }

    private static int countsBrackets(String line, char openedBracket, char closedBracket) {
        char[] symbols = line.toCharArray();
        int openCharSymbol = FIRST_POSITION, closeCharSymbol = FIRST_POSITION;
        for (char character : symbols) {
            if (character == openedBracket) openCharSymbol++;
            if (character == closedBracket) closeCharSymbol++;
        }
        if (openCharSymbol != closeCharSymbol) return ZERO_POINT;
        else return openCharSymbol;
    }

    private static boolean findsTypesBrackets(String line, char openedBracket, char closedBracket) {
        char[] symbols = line.toCharArray();
        int openCharSymbol = FIRST_POSITION, closeCharSymbol = FIRST_POSITION;
        for (char character : symbols) {
            if (character == openedBracket) openCharSymbol++;
            if (character == closedBracket) closeCharSymbol++;
        }
        if (openCharSymbol != closeCharSymbol) return false;
        else {
            for (char character : symbols) {
                if (character == openedBracket) {
                    break;
                }
                if (character == closedBracket) {
                    return false;
                }
            }
            for (int signs = FIRST_INDEX; signs < symbols.length; signs++) {
                if (symbols[signs] == openedBracket) {
                    for (int types = signs; types < symbols.length; types++) {
                        if (symbols[types] == closedBracket) {
                            symbols[signs] = ZERO_POINT;
                            symbols[types] = ZERO_POINT;
                            StringBuilder builder = new StringBuilder(EMPTY_STRING);
                            for (int note = signs; note < types; note++) {
                                builder.append(symbols[note]);
                            }
                            if (!findsAllBrackets(builder.toString())) {
                                symbols[signs] = openedBracket;
                                symbols[types] = closedBracket;
                            } else return true;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
