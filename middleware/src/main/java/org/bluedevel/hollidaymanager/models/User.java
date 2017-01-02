package org.bluedevel.hollidaymanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Nightcrawler on 20.12.2016.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String userName;

    @NotNull
    private String pswd;

    @NotNull
    private int vacationDays;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @NotNull
    @ManyToOne
    private Role role;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    // Each user has its own default week of workdays, which can be changed
    @NotNull
    private WorkdayDefinition workdayDefinition;

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
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

    public WorkdayDefinition getWorkdayDefinition() {
        return workdayDefinition;
    }

    public void setWorkdayDefinition(WorkdayDefinition workdayDefinition) {
        this.workdayDefinition = workdayDefinition;
    }

}
