package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nightcrawler on 02.01.2017.
 */
@RestController
public class UserResource {

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/user/all")
    public Iterable<User> getAllUsers() {
        return userDao.findAll();
    }

    @RequestMapping(path = "/user")
    public User getUserById(@RequestParam("id") Long id) {
        return userDao.findOne(id);
    }
}
