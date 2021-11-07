package ua.com.alevel.service.requests;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.math.BigInteger;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static java.lang.System.out;

@Log4j2
public class RequestsValidInputInCLI {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @SneakyThrows
    public static BigInteger requestAddingIncomeOperation(BufferedReader readerFromConsole) {
        BigInteger incomeValue = null;
        out.println("Please, enter your income value");
        Integer value = Integer.valueOf(readerFromConsole.readLine());
        if (value == 0) {
            out.println("Your input value is '0'- it is not valid");
            out.println("Try again");
            requestAddingIncomeOperation(readerFromConsole);
        } else {
            BigInteger valueOf = BigInteger.valueOf(Integer.parseInt(String.valueOf(value)));
            incomeValue = valueOf.abs();
        }
        return incomeValue;
    }

    @SneakyThrows
    public static BigInteger requestAddingExpenseOperation(BufferedReader readerFromConsole) {
        out.println("Please, enter your income value");
        out.println("Be attentive and please enter your value with minus");
        Integer value = Integer.valueOf(readerFromConsole.readLine());
        if (value >= 0) {
            out.println("Your input value is positive or equals '0' - it is not valid");
            out.println("Try again");
            requestAddingExpenseOperation(readerFromConsole);
        }
        return BigInteger.valueOf(Integer.parseInt(String.valueOf(value)));
    }

    @SneakyThrows
    public static Instant requestAddingDateFrom(BufferedReader readerFromConsole) {
        out.println("Enter the date from which you want to receive data in next format - yyyy-mm-ddThh:ss:mmZ");
        return Instant.parse(String.format(readerFromConsole.readLine(), formatter));
    }

    @SneakyThrows
    public static Instant requestAddingDateTo(BufferedReader readerFromConsole) {
        out.println("Enter the date to which you want to receive data in next format - yyyy-mm-ddThh:ss:mmZ");
        return Instant.parse(String.format(readerFromConsole.readLine(), formatter));
    }


}
