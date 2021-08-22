package ua.com.alevel.exception;

import static java.lang.System.err;
import static java.lang.System.out;

public class EmptyRegistrationBaseException extends Exception {

    public void printErrorEmptyRegistrationBaseExceptionMessage(EmptyRegistrationBaseException emptyBase) {
        err.println(emptyBase.getMessage());
        out.println();
    }

    public String getMessage() {
        return "Registration Base is empty";
    }
}
