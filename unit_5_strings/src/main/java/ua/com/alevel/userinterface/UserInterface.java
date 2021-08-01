package ua.com.alevel.userinterface;


import stringreverslibraries.StringReverse;

import java.util.Scanner;

public class UserInterface {

    private final static int FIRST_INDEX_OF_ARRAY = 0;
    private static final int ONE_CHOICE_OF_USER = 1;
    private static final int SECOND_CHOICE_OF_USER = 2;

    private static final String WELCOME_TO_THE_PROGRAM = "Welcome to unit_4! 'The goal is to extend the method of reverse classes StringBuilder / Buffer. '";
    private static final String DESCRIPTION_OF_METHODS = "Here's what you can choose" + "\n" +
            "1) normal reverse, clicking - '1' " + "\n" +
            "2) reverse on the specified substring in the string, clicking - '2' " + "\n" +
            "3) reverse from which symbol index, string or CharSequence start / end , clicking- '3'" + "\n" +
            "4) reverse, from which string or CharSequence start / end, clicking - '4'" + "\n" +
            "5) if you want exit the Unit clicking - '0' ";
    private static final String INPUT_USER_CHOICE = "Enter your String";
    private static final String WRONG_INPUT_CHOOSE = "Your input is incorrect. Be attentive!";
    private static final String USER_CHOICE_FOR_FIRST_METHOD = "reverse all string clicking '1' " + " \n" + " 2 - reverse each world, clicking '2'";
    private static final String USER_INPUT_SUBSTRING = "Enter a substring, you want to reverse";
    private static final String USER_INPUT_FIRST_INDEX = "Enter a first index and press 'Enter' ";
    private static final String USER_INPUT_SECOND_INDEX = "Enter second index and press 'Enter'";
    private static final String USER_INPUT_FIRST_CHAR = "Enter a first character and press 'Enter' ";
    private static final String USER_INPUT_SECOND_CHAR = "Enter second character and press 'Enter'";

    public void startsUnit() {
        Scanner userChoose = new Scanner(System.in);
        while (true) {
            System.out.println(WELCOME_TO_THE_PROGRAM);
            System.out.println(DESCRIPTION_OF_METHODS);
            String userInputData = userChoose.nextLine();
            switch (userInputData) {
                case "1": {
                    System.out.println(INPUT_USER_CHOICE);
                    String string = userChoose.nextLine();
                    System.out.println(USER_CHOICE_FOR_FIRST_METHOD);
                    Scanner secondUserChoose = new Scanner(System.in);
                    if (!secondUserChoose.hasNextInt()) {
                        System.out.println(WRONG_INPUT_CHOOSE);
                    } else {
                        int selectionOfUser = secondUserChoose.nextInt();
                        if (selectionOfUser == ONE_CHOICE_OF_USER) {
                            System.out.println(StringReverse.reverse(string, true));

                        } else if (selectionOfUser == SECOND_CHOICE_OF_USER) {
                            System.out.println(StringReverse.reverse(string, false));
                        } else {
                            System.out.println(WRONG_INPUT_CHOOSE);
                        }
                    }
                }
                break;
                case "2": {
                    System.out.println(INPUT_USER_CHOICE);
                    String string = userChoose.nextLine();
                    System.out.println(USER_INPUT_SUBSTRING);
                    String substring = userChoose.nextLine();
                    System.out.println(StringReverse.reverse(string, substring));
                }
                break;
                case "3": {
                    int firstIndex, secondIndex;
                    System.out.println(INPUT_USER_CHOICE);
                    String string = userChoose.nextLine();
                    System.out.println(USER_INPUT_FIRST_INDEX + " " + USER_INPUT_SECOND_INDEX);
                    Scanner firstSelection = new Scanner(System.in);
                    Scanner secondSelection = new Scanner(System.in);
                    if (!firstSelection.hasNextInt() && !secondSelection.hasNextInt()) {
                        System.out.println(WRONG_INPUT_CHOOSE);
                    } else {
                        firstIndex = firstSelection.nextInt();
                        secondIndex = secondSelection.nextInt();
                        System.out.println(StringReverse.reverse(string, firstIndex, secondIndex));
                    }
                }
                break;
                case "4": {
                        char firstChar, secondChar;
                        System.out.println(INPUT_USER_CHOICE);
                        String string = userChoose.nextLine();
                        System.out.println(USER_INPUT_FIRST_CHAR + " " + USER_INPUT_SECOND_CHAR);
                        Scanner firstSelection = new Scanner(System.in);
                        Scanner secondSelection = new Scanner(System.in);
                        firstChar = firstSelection.next().charAt(FIRST_INDEX_OF_ARRAY);
                        secondChar = secondSelection.next().charAt(FIRST_INDEX_OF_ARRAY);
                        System.out.println(StringReverse.reverse(string, firstChar, secondChar));
                }
                break;
                case "0": {
                    return;
                }
                default:
                    System.out.println(WRONG_INPUT_CHOOSE);
                    break;
            }
        }
    }
}

