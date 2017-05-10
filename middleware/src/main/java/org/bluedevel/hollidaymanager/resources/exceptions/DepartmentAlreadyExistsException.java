package org.bluedevel.hollidaymanager.resources.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * @author Robin Engel on 04.05.17.
 */
@ResponseStatus(value = CONFLICT, reason = "Department already exists")
public class DepartmentAlreadyExistsException extends Exception {
}
