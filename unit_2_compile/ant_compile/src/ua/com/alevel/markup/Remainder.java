package ua.com.alevel.markup;

import org.joda.time.DateTime;
import java.util.Locale;

public class Remainder {
    DateTime theCurrentDate = new DateTime();

    public void showRemainder() {
        String currentDayOfWeeK = theCurrentDate.dayOfWeek().getAsText(Locale.US);
        System.out.println("Today is " + currentDayOfWeeK + " it's mean that we learn Ant!");
    }
}
