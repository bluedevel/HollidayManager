package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path = "/department/all")
    public Iterable<UserWorkdayDefinition> getAllUserWorkdayDefinitions(){
        return userWorkdayDefinitionDao.findAll();
    }

    @RequestMapping(path = "/department")
    public UserWorkdayDefinition getUserWorkdayDefinitionById(@RequestParam("id") Long id){
        return userWorkdayDefinitionDao.findOne(id);
    }
}
