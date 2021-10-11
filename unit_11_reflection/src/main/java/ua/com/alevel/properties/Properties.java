package ua.com.alevel.properties;

import ua.com.alevel.annotattions.PropertyKey;

import java.util.Date;

import static ua.com.alevel.util.ConstantsApplication.*;

public class Properties {

    @PropertyKey(value = DURATION_PROCESS)
    public long durationOfTheProcess;

    @PropertyKey(value = OPTIMISTIC_PUBLICATION)
    public double optimisticPublication;

    @PropertyKey(value = DATE)
    public Date date;

    @PropertyKey(value = NOTIFICATION_RESULT)
    public String resultNotification;

    @PropertyKey(value = MESSAGE_PROGRAM)
    public String branchMessages;
}
