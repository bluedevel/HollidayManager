package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Robin Engel
 */
@Component
public class WorkdayService {

    private Map<Integer, Function<WorkdayDefinition, Boolean>> days = new HashMap<>();

    public WorkdayService() {
        days.put(Calendar.MONDAY, WorkdayDefinition::isMonday);
        days.put(Calendar.TUESDAY, WorkdayDefinition::isTuesday);
        days.put(Calendar.WEDNESDAY, WorkdayDefinition::isWednesday);
        days.put(Calendar.THURSDAY, WorkdayDefinition::isThursday);
        days.put(Calendar.FRIDAY, WorkdayDefinition::isFriday);
        days.put(Calendar.SATURDAY, WorkdayDefinition::isSaturday);
        days.put(Calendar.SUNDAY, WorkdayDefinition::isSunday);
    }

    public long getUsedDays(User user) {
        return getUsedDays(user.getUserWorkdayDefinition(), user.getHolidays());
    }

    public long getUsedDays(User user, Holiday newHoliday) {
        Set<Holiday> holidays = new HashSet<>(user.getHolidays());
        holidays.add(newHoliday);
        return getUsedDays(user.getUserWorkdayDefinition(), holidays);
    }

    private long getUsedDays(WorkdayDefinition workdayDefinition, Collection<Holiday> holidays) {
        return holidays.stream()
                .flatMap(h -> h.getDays().stream())
                .filter(d -> isWorkday(workdayDefinition, d))
                .count();
    }

    private boolean isWorkday(WorkdayDefinition userWorkdayDefinition, Calendar calendar) {
        return days.get(
                calendar.get(Calendar.DAY_OF_WEEK))
                .apply(userWorkdayDefinition);
    }
}
