package ua.com.alevel.exception;

import static ua.com.alevel.util.ClassConstants.WRONG_DATA_ENTRY;

public class DateException extends Exception {

    private String message = WRONG_DATA_ENTRY;

    public DateException(String dataUserInput) {
        message += dataUserInput;
    }

    @Override
    public String toString() {
        return message;
    }
}
