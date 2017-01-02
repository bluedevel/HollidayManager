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
