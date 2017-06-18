package org.bluedevel.hollidaymanager.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Robin Engel
 */
@RestController
@RequestMapping("/login")
public class LoginResource {

    /**
     * This controller exists merely for the purpose of verifying credentials.
     * At a later point, a session key or token could be generated here.
     */
    @RequestMapping(method = GET)
    public void login() {
    }

}
