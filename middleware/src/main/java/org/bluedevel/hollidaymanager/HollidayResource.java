package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Holliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(path = "/hollidays")
    public Iterable<Holliday> getAllHollidays() {
        return hollidayDao.findAll();
    }

    @RequestMapping(path = "/hollidays/{id}")
    public Holliday getHollidayById(@PathVariable("id") Long id) {
        return hollidayDao.findOne(id);
    }

    @RequestMapping(path = "/hollidays/add")
    public void setHolliday(Holliday holliday) {
        hollidayDao.save(holliday);
    }

    @RequestMapping(path = "/hollidays/{id}/delete")
    public void deleteOneHolliday(@PathVariable("id") Long id) {
        hollidayDao.delete(id);
    }

    @RequestMapping(path = "/hollidays/delete")
    public void deleteAllHollidays() {
        hollidayDao.deleteAll();
    }

    @RequestMapping(path = "/hollidays/count")
    public Long countHollidays() {
        return hollidayDao.count();
    }
}
