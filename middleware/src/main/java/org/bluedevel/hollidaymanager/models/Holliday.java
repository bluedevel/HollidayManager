package org.bluedevel.hollidaymanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nightcrawler on 20.12.2016.
 */
@Entity
@Table(name = "hollidays")
public class Holliday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar from;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar to;

    @NotNull
    private boolean approved;

    @ManyToOne
    @NotNull
    private User user;

    // One holliday can have many exceptions -> OneToMany, unidirectional
    private Set<WorkdayException> workdayExceptions = new HashSet<WorkdayException>();

    public long getId() {
        return id;
    }

    public Calendar getFrom() {
        return from;
    }

    public void setFrom(Calendar from) {
        this.from = from;
    }

    public Calendar getTo() {
        return to;
    }

    public void setTo(Calendar to) {
        this.to = to;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<WorkdayException> getWorkdayExceptions() {
        return workdayExceptions;
    }

    public void setWorkdayExceptions(Set<WorkdayException> workdayExceptions) {
        this.workdayExceptions = workdayExceptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Holliday holliday = (Holliday) o;

        if (id != holliday.id) return false;
        if (approved != holliday.approved) return false;
        if (from != null ? !from.equals(holliday.from) : holliday.from != null) return false;
        if (to != null ? !to.equals(holliday.to) : holliday.to != null) return false;
        if (user != null ? !user.equals(holliday.user) : holliday.user != null) return false;
        return workdayExceptions.equals(holliday.workdayExceptions);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (approved ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + workdayExceptions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Holliday{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", approved=" + approved +
                ", user=" + user +
                ", workdayExceptions=" + workdayExceptions +
                '}';
    }
}
