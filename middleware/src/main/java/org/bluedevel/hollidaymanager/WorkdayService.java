package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
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

    public boolean isWorkday(Calendar calendar, WorkdayDefinition workdayDefinition) {
        return days.get(
                calendar.get(Calendar.DAY_OF_WEEK))
                .apply(workdayDefinition);
    }
}
