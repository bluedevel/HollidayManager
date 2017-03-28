package org.bluedevel.hollidaymanager.resources;

import org.apache.commons.lang3.StringUtils;
import org.bluedevel.hollidaymanager.DepartmentDao;
import org.bluedevel.hollidaymanager.UserDao;
import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.resources.exceptions.DepartmentNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Robin Engel
 */
@RestController
@RequestMapping(path = "/users")
public class UserResource {

    private final UserDao userDao;
    private final DepartmentDao departmentDao;

    @Autowired
    public UserResource(UserDao userDao, DepartmentDao departmentDao) {
        this.userDao = userDao;
        this.departmentDao = departmentDao;
    }

    @RequestMapping(path = "/{name}", method = GET)
    public User getUser(@PathVariable("name") String name) {
        return userDao.findByUsername(name);
    }

    @RequestMapping(method = PUT)
    public void addUser(@RequestBody User user) throws DepartmentNotFoundExecption {
        String departmentName = Optional.ofNullable(user.getDepartment())
                .map(Department::getName)
                .filter(StringUtils::isEmpty)
                .orElseThrow(DepartmentNotFoundExecption::new);

        Department existingDepartment = departmentDao
                .findByName(departmentName)
                .findFirst()
                .orElseThrow(DepartmentNotFoundExecption::new);

        user.setDepartment(existingDepartment);
        userDao.save(user);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(e, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DepartmentNotFoundExecption.class)
    public ResponseEntity<?> handleDepartmentNotFound(Exception e) {
        return new ResponseEntity<>("Department not found", NOT_FOUND);
    }
}
