package ua.com.alevel.uniquesymbols;

import java.util.Scanner;

import static ua.com.alevel.uniquesymbols.UniqueSymbols.countsUniqueNumericCharactersInAnArray;

public class DataRequest {

    private static final int INDEX_OF_ARRAY = 0;
    private static final String USER_INPUT_LENGTH_OF_ARRAY = "Input Array size:";
    private static final String USER_INPUT_NUMBERS = "Enter your numbers ";

    public int printsARequestForDataEntryASizeAndNumbersOfArray() {
        System.out.println(USER_INPUT_LENGTH_OF_ARRAY);
        Scanner inputUser = new Scanner(System.in);
        int arrayLong = inputUser.nextInt();
        int[] numericSymbols = new int[arrayLong];
        System.out.println(USER_INPUT_NUMBERS);
        for (int digit = INDEX_OF_ARRAY; digit < numericSymbols.length; digit++) {
            numericSymbols[digit] = inputUser.nextInt();
        }
        return countsUniqueNumericCharactersInAnArray(numericSymbols);
    }
}
