package ua.com.alevel.uniquename;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static ua.com.alevel.util.Constants.ONE_INDEX;
import static ua.com.alevel.util.Constants.SPACE;
import static ua.com.alevel.util.RequestAndMessageInformationModuleTheSecond.*;

public class UniqueNameFinder {

    private static final Scanner inputConsole = new Scanner(System.in);
    private static final String filepath = "module_2/src/main/resources/files/names.txt";

    public static void inputsUserDataInformationInConsole() {
        printsInformationNamesInput();
        String[] listOfTheNames = inputConsole.nextLine().split(SPACE);
        ArrayList<String> listOfNames = new ArrayList<>(Arrays.asList(listOfTheNames));
        printsMessageInformationUniqueName();
        System.out.println(findsFirstUniqueNameFromTheList(listOfNames));
    }

    public static void loadsInformationFromFile() {
        System.out.println();
        try {
            Path filePath = Paths.get(filepath);
            ArrayList<String> namesFromTheFile = (ArrayList<String>) Files.readAllLines(filePath);
            printsMessageInformationListNameFromFile();
            namesFromTheFile.forEach(name -> System.out.print(name + SPACE));
            printsMessageInformationUniqueName();
            System.out.println(findsFirstUniqueNameFromTheList(namesFromTheFile));
        } catch (IOException wrongInputOrOutput) {
            wrongInputOrOutput.printStackTrace();
        }
    }

    public static String findsFirstUniqueNameFromTheList(List<String> allNames) {
        LinkedHashMap<String, Integer> countsOfNames = new LinkedHashMap<>();
        for (String name : allNames) {
            countsOfNames.compute(name, (key, value) -> (value == null) ? ONE_INDEX : value + ONE_INDEX);
        }
        for (Map.Entry<String, Integer> entrance : countsOfNames.entrySet()) {
            if (entrance.getValue() == ONE_INDEX) {
                return entrance.getKey();
            }
        }
        return null;
    }
}
