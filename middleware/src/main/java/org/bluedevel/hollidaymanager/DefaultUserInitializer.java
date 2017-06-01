package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.DepartmentDao;
import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.StreamSupport;

import static org.bluedevel.hollidaymanager.models.Role.ADMIN;

/**
 * @author Robin Engel
 */
@Component
public class DefaultUserInitializer implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(DefaultUserInitializer.class);

    private final UserDao userDao;
    private final DepartmentDao departmentDao;
    private final PasswordHasher passwordHasher;

    @Autowired
    public DefaultUserInitializer(UserDao userDao, DepartmentDao departmentDao, PasswordHasher passwordHasher) {
        this.userDao = userDao;
        this.departmentDao = departmentDao;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public void run(String... strings) throws Exception {
        boolean adminExists = StreamSupport.stream(userDao.findAll().spliterator(), false)
                .anyMatch(u -> u.getRole() == ADMIN);

        if (!adminExists) {
            Department department = new Department();
            department.setName("default");
            departmentDao.save(department);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordHasher.hash("admin"));
            admin.setRole(ADMIN);

            admin.setDepartment(department);
            admin.setVacationDays(0);
            admin.setUserWorkdayDefinition(new UserWorkdayDefinition());
            admin.setFirstName("");
            admin.setLastName("");

            userDao.save(admin);

            log.warn("No admin user was found! " +
                    "Created a default one with credentials: username=admin; password=admin");
        }
    }
}
