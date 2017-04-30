package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.WorkdayService;
import org.bluedevel.hollidaymanager.daos.HolidayDao;
import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.models.Holiday;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.resources.exceptions.NoMoreHolidayLeftException;
import org.bluedevel.hollidaymanager.resources.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Robin Engel
 */
@RestController
@RequestMapping("/holiday")
public class HolidayResource extends AbstractResource {

    private UserDao userDao;
    private HolidayDao holidayDao;
    private WorkdayService workdayService;

    @Autowired
    public HolidayResource(UserDao userDao, HolidayDao holidayDao, WorkdayService workdayService) {
        this.userDao = userDao;
        this.holidayDao = holidayDao;
        this.workdayService = workdayService;
    }

    @RequestMapping(path = "{user}", method = PUT)
    public void register(@PathVariable("user") String userName, @RequestBody Holiday newHoliday) throws UserNotFoundException, NoMoreHolidayLeftException {
        User user = userDao.findByUsername(userName)
                .orElseThrow(UserNotFoundException::new);

        if (workdayService.getUsedDays(user, newHoliday) >= user.getVacationDays()) {
            throw new NoMoreHolidayLeftException();
        }

        newHoliday.setUser(user);
        holidayDao.save(newHoliday);
    }
}