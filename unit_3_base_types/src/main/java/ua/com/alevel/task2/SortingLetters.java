package ua.com.alevel.task2;

import java.util.Arrays;

public class SortingLetters {

    private static final int START_COUNT_LETTERS = 0;
    private static final int FIRST_ARRAY_INDEX = 0;

    public void sortedCountEachLetterAndDisplay(String textLine) {
        String nonSortedAlphabet = deletesNotLetters(textLine);
        char[] alphabet = createArray(nonSortedAlphabet);
        String letters = sortsAlphabetically(alphabet);
        countsOccurrencesEachLetter(letters);
    }

    private String deletesNotLetters(String textLine) {
        return textLine.replaceAll("[^\\p{IsAlphabetic}]", "");
    }

    private char[] createArray(String inputLine) {
        return inputLine.toCharArray();
    }

    private String sortsAlphabetically(char[] letters) {
        Arrays.sort(letters);
        return new String(letters);
    }

    private void countsOccurrencesEachLetter(String stringLine) {
        char[] sortedLetters = createArray(stringLine);
        int enumerator = START_COUNT_LETTERS;
        for (int indexOfElement = FIRST_ARRAY_INDEX; indexOfElement < sortedLetters.length; indexOfElement++) {
            char letter = stringLine.charAt(indexOfElement);
            if (stringLine.indexOf(letter) < indexOfElement)
                continue;
            for (int letterNumber = FIRST_ARRAY_INDEX; letterNumber < stringLine.length(); letterNumber++) {
                if (stringLine.charAt(letterNumber) == letter)
                    enumerator++;
            }
            System.out.println(letter + " - " + enumerator);
            enumerator = START_COUNT_LETTERS;
        }
    }
}
