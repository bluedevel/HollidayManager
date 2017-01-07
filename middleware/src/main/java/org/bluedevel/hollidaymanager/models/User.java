package org.bluedevel.hollidaymanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Nightcrawler on 20.12.2016.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "USERNAME")
    private String userName;

    @NotNull
    @Column(name = "PASSWORD")
    private String pswd;

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

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="USER_WORKDAY_DEFINITION", referencedColumnName="id")
    @NotNull
    private UserWorkdayDefinition userWorkdayDefinition;

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

    public UserWorkdayDefinition getUserWorkdayDefinition() {
        return userWorkdayDefinition;
    }

    public void setUserWorkdayDefinition(UserWorkdayDefinition userWorkdayDefinition) {
        this.userWorkdayDefinition = userWorkdayDefinition;
    }

}
