package org.bluedevel.hollidaymanager.resources.dto;

import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.models.Role;
import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;

/**
 * @author Robin Engel
 */
public class NewUserDto extends UserDto {

    private String password;

    public NewUserDto() {
    }

    public NewUserDto(String username, String password, int vacationDays, Department department, Role role, String firstName, String lastName, UserWorkdayDefinition workdayDefinition) {
        super(username, vacationDays, department, role, firstName, lastName, workdayDefinition);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
