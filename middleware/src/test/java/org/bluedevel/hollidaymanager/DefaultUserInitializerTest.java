package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.DepartmentDao;
import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;

import static org.bluedevel.hollidaymanager.models.Role.ADMIN;
import static org.bluedevel.hollidaymanager.models.Role.USER;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Robin Engel
 */
@RunWith(JUnit4.class)
public class DefaultUserInitializerTest {

    @Mock
    private UserDao userDaoMock;

    @Mock
    private DepartmentDao departmentDaoMock;

    @Mock
    private PasswordHasher passwordHasherMock;

    @InjectMocks
    private DefaultUserInitializer defaultUserInitializer;

    @Before
    public void setup() throws NoSuchAlgorithmException {
        initMocks(this);
        //defaultUserInitializer = new DefaultUserInitializer(userDaoMock, departmentDaoMock, passwordHasherMock);

        doReturn("mocked password").when(passwordHasherMock).hash(any());
    }

    @Test
    public void testCreatedNoUsers() throws Exception {
        doReturn(Collections.emptyList()).when(userDaoMock).findAll();

        defaultUserInitializer.run();

        verify(userDaoMock, times(1)).findAll();
        verify(userDaoMock, times(1)).save(any(User.class));
    }

    @Test
    public void testCreatedNoAdmins() throws Exception {
        User user1 = new User();
        User user2 = new User();
        user1.setRole(USER);
        user2.setRole(USER);
        doReturn(Arrays.asList(user1, user2)).when(userDaoMock).findAll();

        defaultUserInitializer.run();

        verify(userDaoMock, times(1)).findAll();
        verify(userDaoMock, times(1)).save(any(User.class));
    }

    @Test
    public void testNotCreated() throws Exception {
        User user1 = new User();
        User user2 = new User();
        user1.setRole(USER);
        user2.setRole(ADMIN);
        doReturn(Arrays.asList(user1, user2)).when(userDaoMock).findAll();

        defaultUserInitializer.run();

        verify(userDaoMock, times(1)).findAll();
        verify(userDaoMock, times(0)).save(any(User.class));
    }
}
