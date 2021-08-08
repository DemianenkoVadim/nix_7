package ua.com.alevel.uniquesymbols;

public class UniqueSymbols {

    private static final int START_INDEX_IN_ARRAY = 0;
    private static final int START_POINT_COUNTER = 0;
    private static final int ONE_UNIQUE_NUMERIC_SYMBOLS = 1;
    private static final int VALUE_IN_ARRAY_OF_ZEROS = 0;
    private static final int NULL_VALUE_UNIQUE_NUMERIC_CHARACTERS = 0;
    private static final int NULL_VALUE_NUMERIC_CHARACTERS = 0;

    public static int countsUniqueNumericCharactersInAnArray(int[] numericCharacters) {
        int[] zeroValuesInArray = new int[numericCharacters.length];
        int counterUniqueNumber = START_POINT_COUNTER;
        int countNumericCharacters = NULL_VALUE_NUMERIC_CHARACTERS;
        int numberOfUniqueNumericCharacters = NULL_VALUE_UNIQUE_NUMERIC_CHARACTERS;

        for (int digit = START_INDEX_IN_ARRAY; digit < numericCharacters.length; digit++) {
            if (zeroValuesInArray[digit] == VALUE_IN_ARRAY_OF_ZEROS) {
                for (int symbol = START_INDEX_IN_ARRAY; symbol < numericCharacters.length; symbol++) {
                    if (numericCharacters[digit] == numericCharacters[symbol]) {
                        zeroValuesInArray[symbol] = ONE_UNIQUE_NUMERIC_SYMBOLS;
                        counterUniqueNumber++;
                    }
                }
            }
            if (countNumericCharacters < counterUniqueNumber) {
                countNumericCharacters = counterUniqueNumber;
                numberOfUniqueNumericCharacters++;
            }
        }
        return numberOfUniqueNumericCharacters;
    }
}
