package ua.com.alevel.brackets;

import java.util.Scanner;

public class InputDataRequest {

    private static final String USER_INPUT = "Enter your String text ";

    public void printsARequestForDataEntryASizeAndNumbersOfArray() {
        Scanner userInputStringText = new Scanner(System.in);
        System.out.println(USER_INPUT);
        String stringInput = userInputStringText.nextLine();
        new BracketChecker().makeCheckOfBrackets(stringInput);
    }
}
