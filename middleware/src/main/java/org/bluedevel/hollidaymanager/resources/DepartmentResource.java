package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.DepartmentDao;
import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.resources.exceptions.DepartmentNotFoundExecption;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
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
    public Department getDepartment(@PathVariable("name") String name) throws DepartmentNotFoundExecption {
        return departmentDao.findByName(name)
                .orElseThrow(DepartmentNotFoundExecption::new);
    }

    @RequestMapping(method = PUT)
    public void addDepartment(@RequestBody Department department) {
        departmentDao.save(department);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException() {
        //TODO think of nice error handling
        return new ResponseEntity<>("Constraint violation", BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentNotFoundExecption.class)
    public ResponseEntity<?> handleDepartmentNotFound() {
        return new ResponseEntity<>("Department not found", NOT_FOUND);
    }
}
