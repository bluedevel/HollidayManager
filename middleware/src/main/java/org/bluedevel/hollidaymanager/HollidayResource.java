package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
@RestController
public class HollidayResource {

    @Autowired
    private HollidayDao hollidayDao;

    @RequestMapping(path = "/holliday/all")
    public Iterable<Holliday> getAllHollidays() {
        return hollidayDao.findAll();
    }

    @RequestMapping(path = "/holliday")
    public Holliday getHollidayById(@RequestParam("id") Long id) {
        return hollidayDao.findOne(id);
    }
}
