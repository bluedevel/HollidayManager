package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.DepartmentDao;
import org.bluedevel.hollidaymanager.daos.GlobalWorkdayDefinitionDao;
import org.bluedevel.hollidaymanager.daos.HolidayDao;
import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.daos.UserWorkdayDefinitionDao;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Robin Engel
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HollidaymanagerApplication.class)
public abstract class IntegrationBaseTest {

    @Autowired
    protected UserDao userDao;

    @Autowired
    protected DepartmentDao departmentDao;

    @Autowired
    protected HolidayDao holidayDao;

    @Autowired
    protected GlobalWorkdayDefinitionDao globalWorkdayDefinitionDao;

    @Autowired
    protected UserWorkdayDefinitionDao userWorkdayDefinitionDao;

    @After
    public void cleanupDatabase() {
        this.holidayDao.deleteAll();
        this.userDao.deleteAll();
        this.departmentDao.deleteAll();
        this.globalWorkdayDefinitionDao.deleteAll();
        this.userWorkdayDefinitionDao.deleteAll();
    }
}
