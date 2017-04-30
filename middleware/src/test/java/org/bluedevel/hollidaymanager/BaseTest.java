package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.DepartmentDao;
import org.bluedevel.hollidaymanager.daos.GlobalWorkdayDefinitionDao;
import org.bluedevel.hollidaymanager.daos.HolidayDao;
import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.daos.UserWorkdayDefinitionDao;
import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.models.GlobalWorkdayDefinition;
import org.bluedevel.hollidaymanager.models.Role;
import org.bluedevel.hollidaymanager.models.User;
import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Robin Engel
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HollidaymanagerApplication.class)
@WebAppConfiguration
public abstract class BaseTest {

    protected MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

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

    protected Department departmentCats;
    protected User userHelga;

    @Before
    public void baseSetup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.holidayDao.deleteAll();
        this.userDao.deleteAll();
        this.departmentDao.deleteAll();
        this.globalWorkdayDefinitionDao.deleteAll();
        this.userWorkdayDefinitionDao.deleteAll();

        this.departmentCats = this.departmentDao.save(new Department("Cats"));
        this.userHelga = this.userDao.save(new User("helag1", "fred", 25, this.departmentCats,
                Role.USER, "Helga", "Gerölllheimer",
                new UserWorkdayDefinition(true, true, true, true,
                        true, false, false)));

        this.globalWorkdayDefinitionDao.save(new GlobalWorkdayDefinition(true, true,
                true, true, true, false, false));
    }

}
