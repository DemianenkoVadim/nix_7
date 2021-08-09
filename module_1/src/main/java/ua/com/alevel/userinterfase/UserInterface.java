package ua.com.alevel.userinterfase;

import ua.com.alevel.brackets.InputDataRequest;
import ua.com.alevel.figureknight.ChessHorseStroke;
import ua.com.alevel.gameoflife.GameOfLife;
import ua.com.alevel.trianglearea.Triangle;
import ua.com.alevel.uniquesymbols.DataRequest;

import java.util.Scanner;

public class UserInterface {

    public void printsUserInterface() {
        while (true) {
            System.out.println("Welcome to module 1! Make your Choose: ");
            System.out.println("1 - To count Unique Numeric Characters In An Array");
            System.out.println("2 - ChessHorseStroke");
            System.out.println("3 - To count Area of triangle");
            System.out.println("4 - Make Check Of Brackets in String");
            System.out.println("5 - Game of life");
            System.out.println("6 - exit");
            System.out.print(" So your choice: ");
            int userChoose;
            Scanner scanner = new Scanner(System.in);
            userChoose = scanner.nextInt();
            boolean exit = false;

            switch (userChoose) {
                case (1):
                    System.out.println(new DataRequest().printsARequestForDataEntryASizeAndNumbersOfArray());
                    break;
                case (2):
                    ChessHorseStroke.createsCoordinatesVerticesOfTriangle();
                    break;
                case (3): {
                    new Triangle().createsCoordinatesVerticesOfTriangle();
                }
                break;
                case (4):
                    new InputDataRequest().printsARequestForDataEntryASizeAndNumbersOfArray();
                    break;
                case (5):
                    new GameOfLife().startsTheProgram();
                    break;
                case (6):
                    exit = true;
                    System.out.println("Bye");
                    break;
            }
            if (exit) break;
        }
    }
}
