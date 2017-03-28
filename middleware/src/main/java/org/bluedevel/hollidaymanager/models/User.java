package org.bluedevel.hollidaymanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * Created by Nightcrawler on 20.12.2016.
 */
@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"USER_ID", "USERNAME"}))
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @NotNull
    @Column(name = "VACATION_DAYS")
    private int vacationDays;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @NotNull
    @Column(name = "ROLE")
    private Role role;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    //@JoinColumn(name="USER_WORKDAY_DEFINITION", referencedColumnName="id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotNull
    private UserWorkdayDefinition userWorkdayDefinition;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserWorkdayDefinition getUserWorkdayDefinition() {
        return userWorkdayDefinition;
    }

    public void setUserWorkdayDefinition(UserWorkdayDefinition userWorkdayDefinition) {
        this.userWorkdayDefinition = userWorkdayDefinition;
    }

}
