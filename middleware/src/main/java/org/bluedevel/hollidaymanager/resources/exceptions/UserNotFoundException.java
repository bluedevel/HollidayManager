package org.bluedevel.hollidaymanager.resources.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Robin Engel
 */
@ResponseStatus(value = NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends Exception {
}
