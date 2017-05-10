package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holiday;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Calendar;

import static java.util.Calendar.JUNE;
import static java.util.Calendar.MAY;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
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
        fail();
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
    public void testAddMultipleHolidaysTooLargeWithHalfDays() throws Exception {
        fail();
    }

    private ResultActions putHoliday(Calendar start, Calendar end) throws Exception {
        Holiday newHoliday = new Holiday();

        newHoliday.setStart(start);
        newHoliday.setEnd(end);

        return perform(put("/holiday/" + userHelga.getUsername()), newHoliday);
    }
}