package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(path = "/users")
    public Iterable<User> getAllHollidays() {
        return userDao.findAll();
    }

    @RequestMapping(path = "/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userDao.findOne(id);
    }

    @RequestMapping(path = "/users/add")
    public void setUser(User user) {
        userDao.save(user);
    }

    @RequestMapping(path = "/users/{id}/delete")
    public void deleteOneUser(@PathVariable("id") Long id) {
        userDao.delete(id);
    }

    @RequestMapping(path = "/users/delete")
    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    @RequestMapping(path = "/users/count")
    public Long countUsers() {
        return userDao.count();
    }
}
