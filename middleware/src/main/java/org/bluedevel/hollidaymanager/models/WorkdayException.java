package org.bluedevel.hollidaymanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nightcrawler on 21.12.2016.
 */
@Entity
@Table(name="workday_exceptions")
public class WorkdayException {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar from;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar to;

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

}
