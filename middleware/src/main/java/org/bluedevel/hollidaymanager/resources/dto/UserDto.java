package org.bluedevel.hollidaymanager.resources.dto;

import org.bluedevel.hollidaymanager.models.Department;
import org.bluedevel.hollidaymanager.models.Role;

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
    private WorkdayDefinitionDto workdayDefinition;

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

    public WorkdayDefinitionDto getWorkdayDefinition() {
        return workdayDefinition;
    }

    public void setWorkdayDefinition(WorkdayDefinitionDto workdayDefinition) {
        this.workdayDefinition = workdayDefinition;
    }
}
