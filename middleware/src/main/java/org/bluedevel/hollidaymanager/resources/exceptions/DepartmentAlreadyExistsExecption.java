package org.bluedevel.hollidaymanager.resources.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

/**
 * @author Robin Engel
 */
@ResponseStatus(value = CONFLICT, reason = "Department already exists")
public class DepartmentAlreadyExistsExecption extends Exception {
}
