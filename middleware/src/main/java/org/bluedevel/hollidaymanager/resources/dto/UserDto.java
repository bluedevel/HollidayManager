package org.bluedevel.hollidaymanager.resources.dto;

import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.models.Role;
import org.bluedevel.hollidaymanager.models.UserWorkdayDefinition;

/**
 * @author Robin Engel
 */
public class UserDto {

    private String username;
    private int vacationDays;
    private Department department;
    private Role role;
    private String firstName;
    private String lastName;
    private UserWorkdayDefinition workdayDefinition;

    public UserDto() {
    }

    public UserDto(String username, int vacationDays, Department department, Role role, String firstName, String lastName, UserWorkdayDefinition workdayDefinition) {
        this.username = username;
        this.vacationDays = vacationDays;
        this.department = department;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workdayDefinition = workdayDefinition;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserWorkdayDefinition getWorkdayDefinition() {
        return workdayDefinition;
    }

    public void setWorkdayDefinition(UserWorkdayDefinition workdayDefinition) {
        this.workdayDefinition = workdayDefinition;
    }
}
