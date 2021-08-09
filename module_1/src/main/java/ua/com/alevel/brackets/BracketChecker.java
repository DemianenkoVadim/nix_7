package ua.com.alevel.brackets;

public class BracketChecker {

    public static final int FIRST_INDEX = 0;
    public static final char CURLY_OPENED_BRACE = '{';
    public static final char CURLY_CLOSED_BRACE = '}';
    public static final char SQUARE_OPENED_BRACE = '[';
    public static final char SQUARE_CLOSED_BRACE = ']';
    public static final char OPENED_BRACE = '(';
    public static final char CLOSED_BRACE = ')';

    public void makeCheckOfBrackets(String userInput) {
        int lengthInput = userInput.length();
        StackDataStructure stackData = new StackDataStructure(lengthInput);
        for (int oneElement = FIRST_INDEX; oneElement < lengthInput; oneElement++) {
            char oneSymbol = userInput.charAt(oneElement);
            if (oneSymbol == CURLY_OPENED_BRACE || oneSymbol == SQUARE_OPENED_BRACE || oneSymbol == OPENED_BRACE) {
                stackData.addElementInHeadPosition(oneSymbol);
            } else if (oneSymbol == CURLY_CLOSED_BRACE || oneSymbol == SQUARE_CLOSED_BRACE || oneSymbol == CLOSED_BRACE) {
                if (stackData.isEmpty()) {
                    char oneBraceClosed = (char) stackData.deleteElementFromHeadPosition();
                    if ((oneSymbol == CURLY_CLOSED_BRACE && oneBraceClosed != CURLY_OPENED_BRACE)
                            || (oneSymbol == SQUARE_CLOSED_BRACE && oneBraceClosed != SQUARE_OPENED_BRACE)
                            || (oneSymbol == CLOSED_BRACE && oneBraceClosed != OPENED_BRACE))
                        System.out.println("Mistake! Brace " + oneSymbol + " in " + oneElement + " position.");
                } else
                    System.out.println("Mistake! Brace " + oneSymbol + " in " + oneElement + " position.");
            }
        }
        if (stackData.isEmpty()) {
            System.out.println("Missing closing parenthesis");
        } else {
            System.out.println("Brackets checked");
        }
    }
}
