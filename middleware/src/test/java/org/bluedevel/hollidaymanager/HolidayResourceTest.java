package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.User;
import org.hibernate.Hibernate;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Robin Engel on 04.05.17.
 */
public class HolidayResourceTest extends BaseTest {

    @Test
    @Transactional
    @Ignore
    public void testAddSingleHoliday() throws Exception {
        Holiday newHoliday = new Holiday();

        Calendar start = Calendar.getInstance();
        start.set(2017, 5, 1); // Monday
        Calendar end = Calendar.getInstance();
        end.set(2017, 5, 7);

        newHoliday.setStart(start);
        newHoliday.setEnd(end);

        perform(put("/holiday/" + userHelga.getUsername()), newHoliday)
                .andExpect(status().isOk());

        User user = loadUser(userHelga.getUsername());
        Hibernate.initialize(user.getHolidays());
        Set<Holiday> holidays = user.getHolidays();
        assertThat(holidays.size(), is(1));

        Holiday loadedHoliday = holidays.iterator().next();
        assertThat(loadedHoliday.getStart(), is(start));
        assertThat(loadedHoliday.getEnd(), is(end));
        assertThat(loadedHoliday.getDays().size(), is(7));
    }

    @Test
    public void testAddSingleHolidayTooLarge() throws Exception {

    }

    @Test
    public void testAddMultipleHolidays() throws Exception {

    }

    @Test
    public void testAddMultipleHolidaysTooLarge() throws Exception {

    }

    @Test
    public void testAddMultipleHolidaysTooLargeWithHalfDays() throws Exception {

    }
}