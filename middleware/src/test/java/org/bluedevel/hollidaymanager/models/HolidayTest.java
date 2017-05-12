package org.bluedevel.hollidaymanager.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;

import static java.util.Calendar.MAY;
import static org.junit.Assert.assertEquals;

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
 * @author Robin Engel
 */
@RunWith(JUnit4.class)
public class HolidayTest {

    @Test
    public void getDays() throws Exception {
        Holiday holiday = new Holiday();

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 7);
        end.set(2017, MAY, 27);

        holiday.setStart(start);
        holiday.setEnd(end);

        assertEquals(21, holiday.getDays().size());
    }

}
