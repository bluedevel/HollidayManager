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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (vacationDays != user.vacationDays) return false;
        if (!userName.equals(user.userName)) return false;
        if (!pswd.equals(user.pswd)) return false;
        if (!department.equals(user.department)) return false;
        if (role != user.role) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        return workdayDefinition.equals(user.workdayDefinition);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + userName.hashCode();
        result = 31 * result + pswd.hashCode();
        result = 31 * result + vacationDays;
        result = 31 * result + department.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + workdayDefinition.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", pswd='" + pswd + '\'' +
                ", vacationDays=" + vacationDays +
                ", department=" + department +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", workdayDefinition=" + workdayDefinition +
                '}';
    }
}
