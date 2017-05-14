package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.HollidaymanagerApplication;
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
import org.bluedevel.hollidaymanager.resources.dto.NewUserDto;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
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

    protected final MediaType contentTypeJson = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

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

    protected ResultActions perform(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        return this.mockMvc.perform(requestBuilder);
    }

    protected ResultActions perform(MockHttpServletRequestBuilder requestBuilder, Object data) throws Exception {
        requestBuilder = requestBuilder
                .contentType(contentTypeJson)
                .content(json(data));

        return perform(requestBuilder);
    }

    @SuppressWarnings("unchecked")
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
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
    protected Department newDepartmentDogs;
    protected Department newDepartmentCats;

    protected User userHelga;
    protected NewUserDto newUserHelga;
    protected NewUserDto newUserFranz;

    @Before
    public void baseSetup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.departmentCats = new Department("Cats");
        this.newDepartmentCats = new Department("Cats");

        this.newDepartmentDogs = new Department("Dogs");

        this.userHelga = new User("helag1", "fred", 25, this.departmentCats,
                Role.USER, "Helga", "Gerölllheimer",
                new UserWorkdayDefinition(true, true, true, true,
                        true, false, false));
        this.newUserHelga = new NewUserDto("helag1", "fred", 25, this.departmentCats,
                Role.USER, "Helga", "Gerölllheimer",
                new UserWorkdayDefinition(true, true, true, true,
                        true, false, false));

        this.newUserFranz = new NewUserDto("franz", "fred", 25, this.departmentCats,
                Role.USER, "Franz", "Müller",
                new UserWorkdayDefinition(true, true, true, true,
                        true, false, false));

        this.departmentCats = this.departmentDao.save(this.departmentCats);
        this.userHelga = this.userDao.save(this.userHelga);

        this.globalWorkdayDefinitionDao.save(new GlobalWorkdayDefinition(true, true,
                true, true, true, false, false));
    }

    @After
    public void baseAfter() {
        this.holidayDao.deleteAll();
        this.userDao.deleteAll();
        this.departmentDao.deleteAll();
        this.globalWorkdayDefinitionDao.deleteAll();
        this.userWorkdayDefinitionDao.deleteAll();
    }

}
