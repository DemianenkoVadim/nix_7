package ua.com.alevel.exception;

import static java.lang.System.out;

public class NonExistentIDException extends Exception {

    public void printErrorNonExistentIDExceptionMessage(NonExistentIDException wrongID) {
        out.println(wrongID.getMessage());
        out.println();
    }

    public String getMessage() {
        return "The ID you entered is not in the database";
    }
}
