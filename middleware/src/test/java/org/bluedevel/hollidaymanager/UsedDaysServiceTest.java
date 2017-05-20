package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;
import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;

import static java.util.Calendar.MAY;
import static java.util.Collections.singletonList;
import static junit.framework.TestCase.assertEquals;

/**
 * Used dates for these tests:
 * <pre>
 *        April                  Mai                   June
 * So Mo Di Mi Do Fr Sa  So Mo Di Mi Do Fr Sa  So Mo Di Mi Do Fr Sa
 *                   1       1  2  3  4  5  6               1  2  3
 *  2  3  4  5  6  7  8   7  8  9 10 11 12 13   4  5  6  7  8  9 10
 *  9 10 11 12 13 14 15  14 15 16 17 18 19 20  11 12 13 14 15 16 17
 * 16 17 18 19 20 21 22  21 22 23 24 25 26 27  18 19 20 21 22 23 24
 * 23 24 25 26 27 28 29  28 29 30 31           25 26 27 28 29 30
 * 30
 * </pre>
 *
 * @author Robin Engel on 20.05.17.
 */
@RunWith(JUnit4.class)
public class UsedDaysServiceTest {

    private WorkdayDefinition workdayDefinition = new UserWorkdayDefinition(true, true, true,
            true, true, false, false);

    @Test
    public void testGetUsedDays() {
        UsedDaysService usedDaysService = new UsedDaysService(new WorkdayService());
        Holiday holiday = getTwoWeekHoliday();
        assertEquals(10.0, usedDaysService.getUsedDays(workdayDefinition, singletonList(holiday)));
    }

    @Test
    public void testGetUsedDaysStartsWithHalfDay() {
        UsedDaysService usedDaysService = new UsedDaysService(new WorkdayService());
        Holiday holiday = getTwoWeekHoliday();
        holiday.setStartsWithHalfDay(true);
        assertEquals(9.5, usedDaysService.getUsedDays(workdayDefinition, singletonList(holiday)));
    }

    @Test
    public void testGetUsedDaysEndsWithHalfDay() {
        UsedDaysService usedDaysService = new UsedDaysService(new WorkdayService());
        Holiday holiday = getTwoWeekHoliday();
        holiday.setEndsWithHalfDay(true);
        assertEquals(9.5, usedDaysService.getUsedDays(workdayDefinition, singletonList(holiday)));
    }


    @Test
    public void testGetUsedDaysStartsWithHalfDayOnWeekend() {
        UsedDaysService usedDaysService = new UsedDaysService(new WorkdayService());
        Holiday holiday = getTwoWeekHolidayStartAndEndOnWeekend();
        holiday.setStartsWithHalfDay(true);
        assertEquals(10.0, usedDaysService.getUsedDays(workdayDefinition, singletonList(holiday)));
    }

    @Test
    public void testGetUsedDaysEndsWithHalfDayOnWeekend() {
        UsedDaysService usedDaysService = new UsedDaysService(new WorkdayService());
        Holiday holiday = getTwoWeekHolidayStartAndEndOnWeekend();
        holiday.setEndsWithHalfDay(true);
        assertEquals(10.0, usedDaysService.getUsedDays(workdayDefinition, singletonList(holiday)));
    }

    private Holiday getTwoWeekHoliday() {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 1); // Monday
        end.set(2017, MAY, 12);

        Holiday holiday = new Holiday();
        holiday.setStart(start);
        holiday.setEnd(end);

        return holiday;
    }

    public Holiday getTwoWeekHolidayStartAndEndOnWeekend() {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 13); // Monday
        end.set(2017, MAY, 27);

        Holiday holiday = new Holiday();
        holiday.setStart(start);
        holiday.setEnd(end);

        return holiday;
    }
}
