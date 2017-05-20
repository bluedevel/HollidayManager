package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Collections.unmodifiableMap;

/**
 * @author Robin Engel
 */
@Component
public class WorkdayService {

    private final Map<Integer, Function<WorkdayDefinition, Boolean>> days;

    public WorkdayService() {
        Map<Integer, Function<WorkdayDefinition, Boolean>> days = new HashMap<>();
        days.put(Calendar.MONDAY, WorkdayDefinition::isMonday);
        days.put(Calendar.TUESDAY, WorkdayDefinition::isTuesday);
        days.put(Calendar.WEDNESDAY, WorkdayDefinition::isWednesday);
        days.put(Calendar.THURSDAY, WorkdayDefinition::isThursday);
        days.put(Calendar.FRIDAY, WorkdayDefinition::isFriday);
        days.put(Calendar.SATURDAY, WorkdayDefinition::isSaturday);
        days.put(Calendar.SUNDAY, WorkdayDefinition::isSunday);
        this.days = unmodifiableMap(days);
    }

    public boolean isWorkday(Calendar calendar, WorkdayDefinition workdayDefinition) {
        return days.get(
                calendar.get(Calendar.DAY_OF_WEEK))
                .apply(workdayDefinition);
    }
}
