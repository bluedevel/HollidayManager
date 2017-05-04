package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.daos.DepartmentDao;
import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.resources.exceptions.DepartmentAlreadyExistsException;
import org.bluedevel.hollidaymanager.resources.exceptions.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class DepartmentResource {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentResource(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @RequestMapping("/{name}")
    public Department getDepartment(@PathVariable("name") String name) throws DepartmentNotFoundException {
        return departmentDao.findByName(name)
                .orElseThrow(DepartmentNotFoundException::new);
    }

    @RequestMapping(method = PUT)
    public void addDepartment(@RequestBody Department department) throws DepartmentAlreadyExistsException {
        try {
            departmentDao.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new DepartmentAlreadyExistsException();
        }
    }

}
