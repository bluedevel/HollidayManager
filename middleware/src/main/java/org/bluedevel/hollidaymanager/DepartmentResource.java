package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/departments/add")
    public void setDepartment(Department department) {
        departmentDao.save(department);
    }

    @RequestMapping(path = "/departments/{id}/delete")
    public void deleteOneDepartment(@PathVariable("id") Long id) {
        departmentDao.delete(id);
    }

    @RequestMapping(path = "/departments/delete")
    public void deleteAllDepartments() {
        departmentDao.deleteAll();
    }

    @RequestMapping(path = "/departments/count")
    public Long countDepartments() {
        return departmentDao.count();
    }


}
