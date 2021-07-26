package ua.com.alevel.task3;

public class SchoolTimeTable {

    private static final int START_LESSONS_IN_HOURS = 9;
    private static final int LESSON_DURATION_IN_MINUTES = 45;
    private static final int SHORT_BREAK_MINUTES = 5;
    private static final int LONG_BREAK_MINUTES = 15;
    private static final int ODD_LESSON = 1;
    private static final int EVEN_LESSON = 2;
    private static final int MINUTES_PER_HOURS = 60;

    public String countsTheEndOfLessons(int lesson) {
        int endOfLessons = countsMinutesForStudy(lesson) + countsMinutesForShortBreaks(lesson) + countsMinutesForLongBreaks(lesson);
        return displaysHoursAndMinutes(endOfLessons);
    }

    private int countsMinutesForStudy(int lessonNumber) {
        return LESSON_DURATION_IN_MINUTES * lessonNumber;
    }

    private int countsMinutesForLongBreaks(int schoolLesson) {
        return LONG_BREAK_MINUTES * ((schoolLesson / EVEN_LESSON) - (schoolLesson + ODD_LESSON) % EVEN_LESSON);
    }

    private int countsMinutesForShortBreaks(int schoolClass) {
        return SHORT_BREAK_MINUTES * (schoolClass / EVEN_LESSON);
    }

    private String displaysHoursAndMinutes(int endOfSchoolLesson) {
        int hoursEndLesson = START_LESSONS_IN_HOURS + (endOfSchoolLesson / MINUTES_PER_HOURS);
        int minutesEndLesson = endOfSchoolLesson % MINUTES_PER_HOURS;
        return hoursEndLesson + " " + minutesEndLesson;
    }
}
