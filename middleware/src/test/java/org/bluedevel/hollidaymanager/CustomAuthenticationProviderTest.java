package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.bluedevel.hollidaymanager.models.Role.ADMIN;
import static org.bluedevel.hollidaymanager.models.Role.USER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Robin Engel
 */
@RunWith(JUnit4.class)
public class CustomAuthenticationProviderTest {

    @Mock
    private UserDao userDaoMock;

    @Mock
    private PasswordHasher passwordHasherMock;

    @InjectMocks
    private CustomAuthenticationProvider authenticationProvider;

    @Before
    public void setup() throws NoSuchAlgorithmException {
        initMocks(this);
        doReturn("mocked password").when(passwordHasherMock).hash(any());
    }

    @Test
    public void testAuthenticationSuccessUser() {
        User user = new User();
        user.setUsername("pluto");
        user.setPassword("mocked password");
        user.setRole(USER);
        doReturn(Optional.of(user)).when(userDaoMock).findByUsername("pluto");

        Authentication incomingAuth = new UsernamePasswordAuthenticationToken("pluto", "secret");
        Authentication authentication = authenticationProvider.authenticate(incomingAuth);

        assertEquals(((User) authentication.getPrincipal()).getUsername(), "pluto");
        assertEquals(authentication.getCredentials(), null);
        assertEquals(authentication.getAuthorities().size(), 1);
        assertEquals(authentication.getAuthorities().iterator().next().getAuthority(), USER.name());
    }

    @Test
    public void testAuthenticationSuccessAdmin() {
        User user = new User();
        user.setUsername("master");
        user.setPassword("mocked password");
        user.setRole(ADMIN);
        doReturn(Optional.of(user)).when(userDaoMock).findByUsername("master");

        Authentication incomingAuth =
                new UsernamePasswordAuthenticationToken("master", "supersecret");
        Authentication authentication = authenticationProvider.authenticate(incomingAuth);

        assertEquals(((User) authentication.getPrincipal()).getUsername(), "master");
        assertEquals(authentication.getCredentials(), null);
        assertEquals(authentication.getAuthorities().size(), 1);
        assertEquals(authentication.getAuthorities().iterator().next().getAuthority(), ADMIN.name());
    }

    @Test
    public void testAuthenticationFail() {
        User user = new User();
        user.setUsername("master");
        user.setPassword("not mocked password");
        user.setRole(ADMIN);
        doReturn(Optional.of(user)).when(userDaoMock).findByUsername("master");

        Authentication incomingAuth =
                new UsernamePasswordAuthenticationToken("master", "secret");
        Authentication authentication = authenticationProvider.authenticate(incomingAuth);

        assertNull(authentication);
    }
}