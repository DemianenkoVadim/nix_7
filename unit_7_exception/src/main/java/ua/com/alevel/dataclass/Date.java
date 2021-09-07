package ua.com.alevel.dataclass;

import java.util.Objects;

public class Date {

    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    private static final int ZERO_YEAR = 0;
    private static final int FIRST_MONTH_IN_THE_YEAR = 1;
    private static final int FIRST_DAY_IN_THE_YEAR = 1;
    private static final int STARTING_HOUR_PER_DAY = 0;
    private static final int STARTING_MINUTE_PER_HOUR = 0;
    private static final int STARTING_SECOND_PER_MINUTE = 0;
    private static final int STARTING_MILLISECOND_PER_SECOND = 0;

    public Date() {
        year = ZERO_YEAR;
        month = FIRST_MONTH_IN_THE_YEAR;
        day = FIRST_DAY_IN_THE_YEAR;
        hours = STARTING_HOUR_PER_DAY;
        minutes = STARTING_MINUTE_PER_HOUR;
        seconds = STARTING_SECOND_PER_MINUTE;
        milliseconds = STARTING_MILLISECOND_PER_SECOND;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    @Override
    public String toString() {
        return "Date: " +
                year + " year "
                + month + " month "
                + day + " day "
                + hours + " hours "
                + minutes + " minutes "
                + seconds + " seconds "
                + milliseconds + " milliseconds";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return year == date.year && month == date.month && day == date.day && hours == date.hours && minutes == date.minutes && seconds == date.seconds && milliseconds == date.milliseconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, hours, minutes, seconds, milliseconds);
    }
}
