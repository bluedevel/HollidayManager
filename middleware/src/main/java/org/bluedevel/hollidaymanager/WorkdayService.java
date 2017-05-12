package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.GlobalWorkdayDefinitionDao;
import org.bluedevel.hollidaymanager.models.GlobalWorkdayDefinition;
import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.springframework.beans.factory.annotation.Autowired;
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

    private GlobalWorkdayDefinitionDao globalWorkdayDefinitionDao;

    private Map<Integer, Function<WorkdayDefinition, Boolean>> days = new HashMap<>();

    private WorkdayService() {
        days.put(Calendar.MONDAY, WorkdayDefinition::isMonday);
        days.put(Calendar.TUESDAY, WorkdayDefinition::isTuesday);
        days.put(Calendar.WEDNESDAY, WorkdayDefinition::isWednesday);
        days.put(Calendar.THURSDAY, WorkdayDefinition::isThursday);
        days.put(Calendar.FRIDAY, WorkdayDefinition::isFriday);
        days.put(Calendar.SATURDAY, WorkdayDefinition::isSaturday);
        days.put(Calendar.SUNDAY, WorkdayDefinition::isSunday);
    }

    @Autowired
    public WorkdayService(GlobalWorkdayDefinitionDao globalWorkdayDefinitionDao) {
        this();
        this.globalWorkdayDefinitionDao = globalWorkdayDefinitionDao;
    }

    public double getUsedDays(User user) {
        return getUsedDays(user.getUserWorkdayDefinition(), user.getHolidays());
    }

    public double getUsedDays(User user, Holiday newHoliday) {
        Set<Holiday> holidays = new HashSet<>(user.getHolidays());
        holidays.add(newHoliday);
        return getUsedDays(user.getUserWorkdayDefinition(), holidays);
    }

    private double getUsedDays(WorkdayDefinition workdayDefinition, Collection<Holiday> holidays) {
        Iterable<GlobalWorkdayDefinition> globalWorkdayDefinitions = globalWorkdayDefinitionDao.findAll();

        double usedDays = 0;
        for (Holiday holiday : holidays) {
            usedDays += holiday.getDays().stream()
                    .filter(d -> isWorkday(globalWorkdayDefinitions, workdayDefinition, d))
                    .count();

            if (holiday.isStartsWithHalfDay()) {
                usedDays -= 0.5;
            }

            if (holiday.isEndsWithHalfDay()) {
                usedDays -= 0.5;
            }
        }

        System.out.println(usedDays);

        return usedDays;
    }

    private boolean isWorkday(Iterable<GlobalWorkdayDefinition> globalWorkdayDefinitions, WorkdayDefinition userWorkdayDefinition, Calendar calendar) {
        return days.get(
                calendar.get(Calendar.DAY_OF_WEEK))
                .apply(userWorkdayDefinition);
    }
}
