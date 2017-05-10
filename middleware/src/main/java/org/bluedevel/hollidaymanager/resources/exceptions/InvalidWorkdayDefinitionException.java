package org.bluedevel.hollidaymanager.resources.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author Robin Engel
 */
@ResponseStatus(value = BAD_REQUEST, reason = "Invalid workday definition")
public class InvalidWorkdayDefinitionException extends Exception {
}
