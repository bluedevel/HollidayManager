package org.bluedevel.hollidaymanager;

import org.bluedevel.hollidaymanager.daos.UserDao;
import org.bluedevel.hollidaymanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

/**
 * @author Robin Engel
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDao userDao;
    private final PasswordHasher passwordHasher;

    @Autowired
    public CustomAuthenticationProvider(UserDao userDao, PasswordHasher passwordHasher) {
        this.userDao = userDao;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));

        String hashedPassword;
        try {
            hashedPassword = passwordHasher.hash(password);
        } catch (NoSuchAlgorithmException e) {
            throw new InternalAuthenticationServiceException("Failed to hash received password", e);
        }

        if (hashedPassword.equals(user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, null,
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
