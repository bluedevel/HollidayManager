package org.bluedevel.hollidaymanager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

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
    private Calendar start;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar end;

    @NotNull
    private boolean approved;

    @NotNull
    private boolean startsWithHalfDay;

    @NotNull
    private boolean endsWithHalfDay;

    @ManyToOne
    @NotNull
    private User user;

    public long getId() {
        return id;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isStartsWithHalfDay() {
        return startsWithHalfDay;
    }

    public void setStartsWithHalfDay(boolean startsWithHalfDay) {
        this.startsWithHalfDay = startsWithHalfDay;
    }

    public boolean isEndsWithHalfDay() {
        return endsWithHalfDay;
    }

    public void setEndsWithHalfDay(boolean endsWithHalfDay) {
        this.endsWithHalfDay = endsWithHalfDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
