package ua.com.alevel.util;

import ua.com.alevel.dataclass.Date;

public class CalculatorDates {

    public static long differenceBetweenDates(Date firstInputDate, Date secondInputDate) {
        long firstDateMilliseconds = CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(firstInputDate);
        long secondDateMilliseconds = CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(secondInputDate);
        return Math.abs(firstDateMilliseconds - secondDateMilliseconds);
    }

    public static Date addMillisecondsToDate(Date userInputDate, long millisecondsToAdd) {
        long dateMilliseconds = CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(userInputDate);
        return InverterFromMilliseconds.createsFullDate(dateMilliseconds + millisecondsToAdd);
    }

    public static Date subtractMillisecondsFromDate(Date date, long subtracted) {
        long dateMilliseconds = CalculatorPassedMilliseconds.calculatesTotalMillisecondsPastBeforeUserSpecifiedDataInput(date);
        return InverterFromMilliseconds.createsFullDate(dateMilliseconds - subtracted);
    }
}
