package org.bluedevel.hollidaymanager.resources.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * @author Robin Engel
 */
@ResponseStatus(value = CONFLICT, reason = "No more holiday left")
public class NoMoreHolidayLeftException extends Exception {
}
