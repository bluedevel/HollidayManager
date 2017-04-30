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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nightcrawler on 20.12.2016.
 */
@Entity
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar start;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar end;

    private boolean approved;
    private boolean startsWithHalfDay;
    private boolean endsWithHalfDay;

    @ManyToOne
    private User user;

    public List<Calendar> getDays() {
        List<Calendar> days = new ArrayList<>();

        Calendar cal = start;

        while (cal.compareTo(end) <= 0) {
            days.add(cal);
            cal = (Calendar) cal.clone();
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return days;
    }

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
