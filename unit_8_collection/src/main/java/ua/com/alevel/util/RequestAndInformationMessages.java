package ua.com.alevel.util;

import static ua.com.alevel.util.Constant.*;

public class RequestAndInformationMessages {

    public static void printsMessageWrongIndexes() {
        System.out.println("Indexes are wrong!");
    }

    public static void printsMessageSetOutOfRange() {
        System.out.println("Value is not included in MathSet");
    }

    public static void printsHeadingMenu() {
        System.out.println("------------------------MENU------------------------");
    }

    public static void printsRequestToCreateMathSet() {
        System.out.println("Creating MathSet, choose one of the option below");
    }

    public static void printsRequestToChooseActionToDoWithMathSet() {
        System.out.println("Choose one of the option below, to implement one of the methods");
    }

    public static void printsMenuBarMathSetConstructors() {
        System.out.println();
        printsHeadingMenu();
        printsRequestToCreateMathSet();
        System.out.println();
        System.out.println(MATH_SET_CONSTRUCTOR_FIRST_CHOICE);
        System.out.println(MATH_SET_CONSTRUCTOR_SECOND_CHOICE);
        System.out.println(MATH_SET_CONSTRUCTOR_THIRD_CHOICE);
        System.out.println(MATH_SET_CONSTRUCTOR_FOURS_CHOICE);
        System.out.println(MATH_SET_CONSTRUCTOR_FIVES_CHOICE);
        System.out.println(MATH_SET_CONSTRUCTOR_SIX_CHOICE);
    }

    public static void printsMethodMathSetMenu() {
        printsHeadingMenu();
        printsRequestToChooseActionToDoWithMathSet();
        System.out.println();
        printMenu(FIRST_CHOICE_ADDING_NUMBER, SECOND_CHOICE_ADDING_NUMBER, THIRD_CHOICE_JOIN_NUMBER, FOURS_CHOICE_JOIN_NUMBER,
                FIVES_CHOICE_INTERSECTION_NUMBER, SIX_CHOICE_INTERSECTION_NUMBER, SEVENTH_CHOICE_SORTING_NUMBER, EIGHTS_CHOICE_SORTING_NUMBER,
                NINE_CHOICE_SORTING_NUMBER, TEN_CHOICE_SORTING_NUMBER, ELEVEN_CHOICE_SORTING_NUMBER, TWELVE_CHOICE_SORTING_NUMBER);
        printMenu(THIRTEENTH_CHOICE_PRINT_NUMBER, FOURTEEN_CHOICE_GET_MAX_NUMBER, FIFTEEN_CHOICE_GET_MIN_NUMBER, SIXTEEN_CHOICE_GET_AVERAGE_NUMBER,
                SEVENTEEN_CHOICE_GET_MEDIAN_NUMBER, EIGHTEEN_CHOICE_TO_ARRAY_NUMBER, NINETEEN_CHOICE_TO_ARRAY_NUMBER, TWENTY_CHOICE_CUT_NUMBER,
                TWENTY_ONE_CHOICE_CLEAR_NUMBER, TWENTY_TWO_CHOICE_CLEAR_NUMBER, TWENTY_THREE_CHOICE_GET_NUMBER, ZERO_CHOICE_NUMBER);
    }

    private static void printMenu(String thirteenthChoicePrintNumber, String fourteenChoiceGetMaxNumber, String fifteenChoiceGetMinNumber,
                                  String sixteenChoiceGetAverageNumber, String seventeenChoiceGetMedianNumber, String eighteenChoiceToArrayNumber,
                                  String nineteenChoiceToArrayNumber, String twentyChoiceCutNumber, String twentyOneChoiceClearNumber,
                                  String twentyTwoChoiceClearNumber, String twentyThreeChoiceGetNumber, String zeroChoiceNumber) {
        System.out.println(thirteenthChoicePrintNumber);
        System.out.println(fourteenChoiceGetMaxNumber);
        System.out.println(fifteenChoiceGetMinNumber);
        System.out.println(sixteenChoiceGetAverageNumber);
        System.out.println(seventeenChoiceGetMedianNumber);
        System.out.println(eighteenChoiceToArrayNumber);
        System.out.println(nineteenChoiceToArrayNumber);
        System.out.println(twentyChoiceCutNumber);
        System.out.println(twentyOneChoiceClearNumber);
        System.out.println(twentyTwoChoiceClearNumber);
        System.out.println(twentyThreeChoiceGetNumber);
        System.out.println(zeroChoiceNumber);
    }

    public static void printsMessageUserInputValue() {
        System.out.println("Enter your value");
    }

    public static void printsMessageWrongValue() {
        System.out.println("Your value is wrong. Please, enter another value");
    }

    public static void printsMassageUserAmountValues() {
        System.out.println("Enter number of values you want to add");
    }

    public static void printMessageUserInputMultiplicityValues() {
        System.out.println("Enter your values");
    }

    public static void printMessageUserInputSizeMathSet() {
        System.out.println("Which size of MathSet you want to get? Enter value");
    }

    public static void printMessageUserCompositionMathSet() {
        System.out.println("Enter components of MathSet");
    }

    public static void printMessageUserAmountMathSets() {
        System.out.println("How many MathSets you want to create? Enter value");
    }

    public static void printMessageUserInputMathSetForIntersection() {
        System.out.println("Enter MathSet for intersection:");
    }

    public static void printMessageUserInputIntersectionIs() {
        System.out.println("Intersection is: ");
    }

    public static void printMessageUserNoValue() {
        System.out.println("Empty MathSet");
    }

    public static void printMessageUserAmountMathSetsForIntersection() {
        System.out.println("How many MathSets you want to create for intersection? Enter value");
    }

    public static void printMessageUserFirstIndex() {
        System.out.println("Enter your first index");
    }

    public static void printMessageUserLastIndex() {
        System.out.println("Enter your last index");
    }

    public static void printMessageUserArray() {
        System.out.println("Array:");
    }

    public static void printMessageUserWrongIndex() {
        System.out.println("You entered wrong Indexes");
    }

    public static void printMessageUserClippedMathSet() {
        System.out.println("Clipped MathSet");
    }

    public static void printMessageUserNumbersToClear() {
        System.out.println("How many numbers you want to clear from MathSet? Enter value");
    }

    public static void printMessageUserComponentsToClear() {
        System.out.println("Enter components you want to clear from MathSet");
    }

    public static void printMessageUserInputVolume() {
        System.out.println("Enter volume your MathSet");
    }

    public static void printMessageUserInputSizeOfArray() {
        System.out.println("Enter size of array");
    }

    public static void printMessageUserInputQuantityOfArray() {
        System.out.println("Enter quantity of arrays");
    }

    public static void printsRequestForCreateMathSet() {
        System.out.println("Create MathSet default                                       PRESS 1");
        System.out.println("Create random Math Set                                       PRESS 2");
    }
}
