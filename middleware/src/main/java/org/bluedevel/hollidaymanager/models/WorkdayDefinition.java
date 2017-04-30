package org.bluedevel.hollidaymanager.models;

/**
 * Created by Nightcrawler on 02.01.2017.
 */
public interface WorkdayDefinition {

    boolean isMonday();

    void setMonday(boolean monday);

    boolean isTuesday();

    void setTuesday(boolean tuesday);

    boolean isWednesday();

    void setWednesday(boolean wednesday);

    boolean isThursday();

    void setThursday(boolean thursday);

    boolean isFriday();

    void setFriday(boolean friday);

    boolean isSaturday();

    void setSaturday(boolean saturday);

    boolean isSunday();

    void setSunday(boolean sunday);
};
