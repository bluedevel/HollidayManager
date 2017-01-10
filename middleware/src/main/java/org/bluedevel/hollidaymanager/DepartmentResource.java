package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
@RestController
public class DepartmentResource {

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping(path = "/departments")
    public Iterable<Department> getAllDepartments() {
        return departmentDao.findAll();
    }

    @RequestMapping(path = "/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long id) {
        return departmentDao.findOne(id);
    }

    // just for exambple
    public final BufferedInputStream stupidBullshit() {
        return null;
    }
}
