package stringreverslibraries;

import java.util.StringJoiner;

public class StringReverse {

    private final static int FIRST_INDEX_OF_ARRAY = 0;
    private final static int ONE_INDEX_OF_ARRAY = 1;
    private final static String EMPTY_LINE = "";

    public static String reverse(String userInputStringWorld, boolean allWordsReverse) {
        if (allWordsReverse) {
            return reverseAllString(userInputStringWorld);
        } else {
            return createsResultStringAfterReverse(userInputStringWorld);
        }
    }

    public static String reverse(String userInputStringWorld, String enteredSubstringForReverse) {
        String line = EMPTY_LINE;
        String reservedSubstring = reverse(enteredSubstringForReverse, false);
        String[] wordSplitString = userInputStringWorld.split(enteredSubstringForReverse);
        for (int worldInStringArray = FIRST_INDEX_OF_ARRAY; worldInStringArray < wordSplitString.length - ONE_INDEX_OF_ARRAY; worldInStringArray++) {
            line = EMPTY_LINE + wordSplitString[worldInStringArray] + reservedSubstring;
        }
        return line + wordSplitString[wordSplitString.length - ONE_INDEX_OF_ARRAY];
    }

    public static String reverse(String userInputStringWorld, int startIndexForReverse, int extremeIndexForReverse) {
        String firstPartOfTheInputString = userInputStringWorld.substring(FIRST_INDEX_OF_ARRAY, startIndexForReverse);
        String secondPartOfTheInputString = userInputStringWorld.substring(startIndexForReverse, extremeIndexForReverse + ONE_INDEX_OF_ARRAY);
        String thirdPartOfTheInputString = userInputStringWorld.substring(extremeIndexForReverse);
        String swappingPartOfTheInputString = createsResultStringAfterReverse(secondPartOfTheInputString);
        return firstPartOfTheInputString + swappingPartOfTheInputString + thirdPartOfTheInputString;
    }

    public static String reverse(String userInputStringWorld, char startCharForReverse, char extremeIndexForReverse) {
        int firstIndex = userInputStringWorld.indexOf(startCharForReverse);
        int lastIndex = userInputStringWorld.indexOf(extremeIndexForReverse, firstIndex - ONE_INDEX_OF_ARRAY);
        String substringForReverse = userInputStringWorld.substring(firstIndex, lastIndex + ONE_INDEX_OF_ARRAY);
        return reverse(userInputStringWorld, substringForReverse);
    }

    private static String symbolsSwapping(String word) {
        char[] symbolsInWord = word.toCharArray();
        int leftIndex = FIRST_INDEX_OF_ARRAY;
        int rightIndex = symbolsInWord.length - ONE_INDEX_OF_ARRAY;
        while (leftIndex < rightIndex) {
            swapsCharactersInAWorld(leftIndex, rightIndex, symbolsInWord);
            leftIndex++;
            rightIndex--;
        }
        return new String(symbolsInWord);
    }

    private static void swapsCharactersInAWorld(int leftIndex, int rightIndex, char[] symbolsInWord) {
        char temporaryVariable = symbolsInWord[leftIndex];
        symbolsInWord[leftIndex] = symbolsInWord[rightIndex];
        symbolsInWord[rightIndex] = temporaryVariable;
    }

    private static String createsResultStringAfterReverse(String userInputStringWorld) {
        StringJoiner sequenceOfChars = new StringJoiner(" ");
        for (String word : userInputStringWorld.split(" ")) {
            sequenceOfChars.add(symbolsSwapping(word));
        }
        return sequenceOfChars.toString();
    }

    private static String reverseAllString(String userInputStringWorld) {
        int lengthOfInputString = userInputStringWorld.length();
        char[] sameSizeOfString = new char[lengthOfInputString];
        for (int symbolInArray = FIRST_INDEX_OF_ARRAY; symbolInArray < lengthOfInputString; symbolInArray++) {
            sameSizeOfString[lengthOfInputString - symbolInArray - ONE_INDEX_OF_ARRAY] = userInputStringWorld.charAt(symbolInArray);
        }
        return String.copyValueOf(sameSizeOfString);
    }
}
