package org.bluedevel.hollidaymanager.resources;

import org.bluedevel.hollidaymanager.resources.exceptions.DepartmentNotFoundExecption;
import org.bluedevel.hollidaymanager.resources.exceptions.InvalidWorkdayDefinitionException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.NoSuchAlgorithmException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Robin Engel
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${hollidaymanager.security.passwords.algorithm}")
    private String algorithm;

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<?> handleNoSuchAlgorithmException() {
        return new ResponseEntity<>("Unable to find hash algorithm " + algorithm, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DepartmentNotFoundExecption.class)
    public ResponseEntity<?> handleDepartmentNotFound() {
        return new ResponseEntity<>("Department not found", NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound() {
        return new ResponseEntity<>("User not found", NOT_FOUND);
    }

    @ExceptionHandler(InvalidWorkdayDefinitionException.class)
    public ResponseEntity<?> handleInvalidWorkdayDefinition() {
        return new ResponseEntity<>("Invalid workday definition", BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException() {
        //TODO think of nice error handling
        return new ResponseEntity<>("Constraint violation", BAD_REQUEST);
    }

}
