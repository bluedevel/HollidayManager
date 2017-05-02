package org.bluedevel.hollidaymanager.resources.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Robin Engel
 */
@ResponseStatus(NOT_FOUND)
public class UserNotFoundException extends Exception {
}
