package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.models.Holiday;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Calendar;
import java.util.function.Consumer;

import static java.util.Calendar.JUNE;
import static java.util.Calendar.MAY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
 * @author Robin Engel on 04.05.17.
 */
public class HolidayResourceTest extends BaseTest {

    @Test
    public void testAddSingleHoliday() throws Exception {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 1); // Monday
        end.set(2017, MAY, 26);

        putHoliday(start, end)
                .andExpect(status().isOk());
    }

    @Test
    public void testAddSingleHolidayTooLarge() throws Exception {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 1); // Monday
        end.set(2017, JUNE, 5);

        putHoliday(start, end)
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddMultipleHolidays() throws Exception {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 1); // Monday
        end.set(2017, MAY, 5);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 7);
        end.set(2017, MAY, 9);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 10);
        end.set(2017, MAY, 13);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 17);
        end.set(2017, MAY, 21);

        putHoliday(start, end)
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMultipleHolidaysTooLarge() throws Exception {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 1); // Monday
        end.set(2017, MAY, 5);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 7);
        end.set(2017, MAY, 9);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 10);
        end.set(2017, MAY, 13);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 17);
        end.set(2017, MAY, 21);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 22);
        end.set(2017, JUNE, 7);

        putHoliday(start, end)
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddMultipleHolidaysWithHalfDays() throws Exception {
        put25DaysWithHalfDays();
    }

    @Test
    public void testAddMultipleHolidaysTooLargeWithHalfDays() throws Exception {
        put25DaysWithHalfDays();

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, JUNE, 8);
        end.set(2017, JUNE, 8);

        putHoliday(start, end, h -> h.setEndsWithHalfDay(true))
                .andExpect(status().isConflict());
    }

    private void put25Days() throws Exception {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 1); // Monday
        end.set(2017, MAY, 12);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 15);
        end.set(2017, MAY, 28);

        putHoliday(start, end)
                .andExpect(status().isOk());

        start.set(2017, MAY, 29);
        end.set(2017, JUNE, 4);

        putHoliday(start, end)
                .andExpect(status().isOk());
    }

    private void put25DaysWithHalfDays() throws Exception {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2017, MAY, 1); // Monday
        end.set(2017, MAY, 12);

        putHoliday(start, end, h -> h.setEndsWithHalfDay(true))
                .andExpect(status().isOk());

        start.set(2017, MAY, 15);
        end.set(2017, MAY, 28);

        putHoliday(start, end, h -> h.setStartsWithHalfDay(true))
                .andExpect(status().isOk());

        start.set(2017, MAY, 29);
        end.set(2017, JUNE, 5);

        putHoliday(start, end)
                .andExpect(status().isOk());
    }

    private ResultActions putHoliday(Calendar start, Calendar end) throws Exception {
        return putHoliday(start, end, h -> {
        });
    }

    private ResultActions putHoliday(Calendar start, Calendar end, Consumer<Holiday> config) throws Exception {
        Holiday newHoliday = new Holiday();

        newHoliday.setStart(start);
        newHoliday.setEnd(end);

        config.accept(newHoliday);

        return perform(put("/holiday/" + userHelga.getUsername()), newHoliday);
    }
}