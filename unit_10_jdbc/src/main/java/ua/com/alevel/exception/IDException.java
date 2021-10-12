package ua.com.alevel.exception;

import static ua.com.alevel.util.ApplicationConstants.ID_IS_NOT_EXIST;

public class IDException extends Exception {

    @Override
    public String toString() {
        return ID_IS_NOT_EXIST;
    }
}
