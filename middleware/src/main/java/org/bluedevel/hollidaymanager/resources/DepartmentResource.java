package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.DepartmentDao;
import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.resources.exceptions.DepartmentNotFoundExecption;
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
@RequestMapping("/departments")
public class DepartmentResource extends AbstractResource {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentResource(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @RequestMapping("/{name}")
    public Department getDepartment(@PathVariable("name") String name) throws DepartmentNotFoundExecption {
        return departmentDao.findByName(name)
                .orElseThrow(DepartmentNotFoundExecption::new);
    }

    @RequestMapping(method = PUT)
    public void addDepartment(@RequestBody Department department) {
        departmentDao.save(department);
    }

}
