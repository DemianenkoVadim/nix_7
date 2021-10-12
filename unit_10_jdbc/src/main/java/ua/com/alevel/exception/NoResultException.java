package ua.com.alevel.exception;

import static ua.com.alevel.util.ApplicationConstants.NO_FIND_RESULT;

public class NoResultException extends Exception {

    private final String message;

    public NoResultException(String message) {
        this.message = NO_FIND_RESULT + message;
    }

    @Override
    public String toString() {
        return message;
    }
}
