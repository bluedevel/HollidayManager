package org.bluedevel.hollidaymanager.resources;

import org.apache.commons.lang3.StringUtils;
import org.bluedevel.hollidaymanager.DepartmentDao;
import org.bluedevel.hollidaymanager.PasswordHasher;
import org.bluedevel.hollidaymanager.UserDao;
import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.resources.converter.NewUserConverter;
import org.bluedevel.hollidaymanager.resources.converter.UserConverter;
import org.bluedevel.hollidaymanager.resources.dto.NewUserDto;
import org.bluedevel.hollidaymanager.resources.dto.UserDto;
import org.bluedevel.hollidaymanager.resources.exceptions.DepartmentNotFoundExecption;
import org.bluedevel.hollidaymanager.resources.exceptions.InvalidWorkdayDefinitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Robin Engel
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    //TODO use lombok here {@Slf4j}
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Value("${hollidaymanager.security.passwords.algorithm}")
    private String algorithm;

    private final UserDao userDao;
    private final DepartmentDao departmentDao;
    private final PasswordHasher hasher;

    private final UserConverter userConverter;
    private final NewUserConverter newUserConverter;

    @Autowired
    public UserResource(UserDao userDao, DepartmentDao departmentDao, PasswordHasher hasher,
                        UserConverter userConverter, NewUserConverter newUserConverter) {
        this.userDao = userDao;
        this.departmentDao = departmentDao;
        this.hasher = hasher;
        this.userConverter = userConverter;
        this.newUserConverter = newUserConverter;
    }

    @RequestMapping("/{name}")
    public UserDto getUser(@PathVariable("name") String name) {
        User user = userDao.findByUsername(name);
        return userConverter.toDto(user);
    }

    @RequestMapping(method = PUT)
    public void addUser(@RequestBody NewUserDto user) throws DepartmentNotFoundExecption, NoSuchAlgorithmException, InvalidWorkdayDefinitionException {
        Optional.ofNullable(user.getWorkdayDefinition())
                .orElseThrow(InvalidWorkdayDefinitionException::new);

        String departmentName = Optional.ofNullable(user.getDepartment())
                .map(Department::getName)
                .filter(StringUtils::isNotEmpty)
                .orElseThrow(DepartmentNotFoundExecption::new);

        Department existingDepartment = Optional.ofNullable(departmentDao
                .findByName(departmentName))
                .orElseThrow(DepartmentNotFoundExecption::new);

        user.setDepartment(existingDepartment);

        //TODO handle IllegalArgument correctly; push in manager layer for business logic
        user.setPassword(hasher.hash(user.getPassword()));
        userDao.save(newUserConverter.toModel(user));
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<?> handleNoSuchAlgorithmException() {
        return new ResponseEntity<>("Unable to find hash algorithm " + algorithm, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DepartmentNotFoundExecption.class)
    public ResponseEntity<?> handleDepartmentNotFound() {
        return new ResponseEntity<>("Department not found", NOT_FOUND);
    }

    @ExceptionHandler(InvalidWorkdayDefinitionException.class)
    public ResponseEntity<?> handleInvalidWorkdayDefinition() {
        return new ResponseEntity<>("Invalid workday definition", BAD_REQUEST);
    }
}
