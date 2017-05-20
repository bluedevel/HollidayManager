package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.UsedDaysService;
import org.bluedevel.hollidaymanager.WorkdayDefinitionService;
import org.bluedevel.hollidaymanager.daos.GlobalWorkdayDefinitionDao;
import org.bluedevel.hollidaymanager.daos.HolidayDao;
import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.models.WorkdayDefinition;
import org.bluedevel.hollidaymanager.resources.exceptions.NoMoreHolidayLeftException;
import org.bluedevel.hollidaymanager.resources.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Robin Engel
 */
@RestController
@RequestMapping("/holiday")
public class HolidayResource {

    private UserDao userDao;
    private HolidayDao holidayDao;
    private GlobalWorkdayDefinitionDao globalWorkdayDefinitionDao;
    private UsedDaysService usedDaysService;
    private WorkdayDefinitionService workdayDefinitionService;

    @Autowired
    public HolidayResource(UserDao userDao,
                           HolidayDao holidayDao,
                           GlobalWorkdayDefinitionDao globalWorkdayDefinitionDao,
                           UsedDaysService usedDaysService,
                           WorkdayDefinitionService workdayDefinitionService) {
        this.userDao = userDao;
        this.holidayDao = holidayDao;
        this.globalWorkdayDefinitionDao = globalWorkdayDefinitionDao;
        this.usedDaysService = usedDaysService;
        this.workdayDefinitionService = workdayDefinitionService;
    }

    @RequestMapping(path = "/{user}", method = PUT)
    public void register(@PathVariable("user") String userName, @RequestBody Holiday newHoliday) throws UserNotFoundException, NoMoreHolidayLeftException {
        User user = userDao.findByUsername(userName)
                .orElseThrow(UserNotFoundException::new);

        List<Holiday> holidays = new ArrayList<>(user.getHolidays());
        holidays.add(newHoliday);

        WorkdayDefinition workdayDefinition = workdayDefinitionService.getEffectiveWorkdayDefinition(
                globalWorkdayDefinitionDao.findAll().iterator().next(),
                user.getUserWorkdayDefinition());

        if (usedDaysService.getUsedDays(workdayDefinition, holidays) > user.getVacationDays()) {
            throw new NoMoreHolidayLeftException();
        }

        newHoliday.setUser(user);
        holidayDao.save(newHoliday);
    }
}
