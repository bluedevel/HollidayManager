package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
@RestController
public class UserWorkdayDefinitionResource {

    @Autowired
    private UserWorkdayDefinitionDao userWorkdayDefinitionDao;

    @RequestMapping(path = "/userWorkdayDefinitions")
    public Iterable<UserWorkdayDefinition> getAllUserWorkdayDefinitions() {
        return userWorkdayDefinitionDao.findAll();
    }

    @RequestMapping(path = "/userWorkdayDefinitions/{id}")
    public UserWorkdayDefinition getUserWorkdayDefinitionById(@PathVariable("id") Long id) {
        return userWorkdayDefinitionDao.findOne(id);
    }

    @RequestMapping(path = "/userWorkdayDefinitions/add")
    public void setUserWorkdayDefinition(UserWorkdayDefinition definition) {
        userWorkdayDefinitionDao.save(definition);
    }

    @RequestMapping(path = "/userWorkdayDefinitions/{id}/delete")
    public void deleteOneUserWorkdayDefinition(@PathVariable("id") Long id) {
        userWorkdayDefinitionDao.delete(id);
    }

    @RequestMapping(path = "/userWorkdayDefinitions/delete")
    public void deleteAllUserWorkdayDefinitions() {
        userWorkdayDefinitionDao.deleteAll();
    }

    @RequestMapping(path = "/userWorkdayDefinitions/count")
    public Long countUsers() {
        return userWorkdayDefinitionDao.count();
    }
}
