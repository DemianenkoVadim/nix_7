package ua.com.alevel.task_1;

public class SummationOfNumbers {
    private static final int VALUE_FIRST_NUMBER = 0;
    private static final int ONE_ARRAY_INDEX = 1;
    private static final int FIRST_ARRAY_INDEX = 0;

    public int sumsNumbersTextLine(String textLine) {
        if (textLine == null)
            throw new IllegalArgumentException("The input parameter cannot be null");
        int sumNumbers = VALUE_FIRST_NUMBER;
        char[] signsInTextLine = createAnArray(textLine);
        for (int arrayCell = FIRST_ARRAY_INDEX; arrayCell <= signsInTextLine.length - ONE_ARRAY_INDEX; arrayCell++) {
            if (Character.isDigit(signsInTextLine[arrayCell])) {
                Character symbol = signsInTextLine[arrayCell];
                int digit = stringRepresentation(symbol);
                sumNumbers += digit;
            }
        }
        return sumNumbers;
    }

    private char[] createAnArray(String inputRow) {
        return inputRow.toCharArray();
    }

    private int stringRepresentation(Character digit) {
        String stringRepresentation = digit.toString();
        return Integer.parseInt(stringRepresentation);
    }
}
