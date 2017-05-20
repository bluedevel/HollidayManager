package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Robin Engel
 */
@Component
public class UsedDaysService {

    private WorkdayService workdayService;

    @Autowired
    public UsedDaysService(WorkdayService workdayService) {
        this.workdayService = workdayService;
    }

    public double getUsedDays(WorkdayDefinition workdayDefinition, Collection<Holiday> holidays) {
        double usedDays = 0;
        for (Holiday holiday : holidays) {
            List<Calendar> workdays = holiday.getDays().stream()
                    .filter(d -> workdayService.isWorkday(d, workdayDefinition))
                    .collect(toList());

            usedDays += workdays.size();

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
