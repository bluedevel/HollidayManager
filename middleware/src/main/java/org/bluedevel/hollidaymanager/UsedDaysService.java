package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.GlobalWorkdayDefinitionDao;
import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Robin Engel
 */
@Component
public class UsedDaysService {

    private GlobalWorkdayDefinitionDao globalWorkdayDefinitionDao;
    private WorkdayService workdayService;
    private WorkdayDefinitionService workdayDefinitionService;

    @Autowired
    public UsedDaysService(GlobalWorkdayDefinitionDao globalWorkdayDefinitionDao,
                           WorkdayService workdayService,
                           WorkdayDefinitionService workdayDefinitionService) {
        this.globalWorkdayDefinitionDao = globalWorkdayDefinitionDao;
        this.workdayService = workdayService;
        this.workdayDefinitionService = workdayDefinitionService;
    }

    public double getUsedDays(User user) {
        return getUsedDays(getEffectiveWorkdayDefinition(user), user.getHolidays());
    }

    public double getUsedDays(User user, Holiday newHoliday) {
        Set<Holiday> holidays = new HashSet<>(user.getHolidays());
        holidays.add(newHoliday);
        return getUsedDays(getEffectiveWorkdayDefinition(user), holidays);
    }

    private WorkdayDefinition getEffectiveWorkdayDefinition(User user) {
        return workdayDefinitionService.getEffectiveWorkdayDefinition(
                globalWorkdayDefinitionDao.findAll().iterator().next(),
                user.getUserWorkdayDefinition());
    }

    private double getUsedDays(WorkdayDefinition workdayDefinition, Collection<Holiday> holidays) {
        double usedDays = 0;
        for (Holiday holiday : holidays) {
            usedDays += holiday.getDays().stream()
                    .filter(d -> workdayService.isWorkday(d, workdayDefinition))
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
}
