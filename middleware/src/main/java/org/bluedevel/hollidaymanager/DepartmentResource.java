package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nightcrawler on 03.01.2017.
 */
@RestController
public class DepartmentResource {

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping(path = "/department/all")
    public Iterable<Department> getAllDepartments(){
        return departmentDao.findAll();
    }

    @RequestMapping(path = "/department")
    public Department getDepartmentById(@RequestParam("id") Long id){
        return departmentDao.findOne(id);
    }
}
