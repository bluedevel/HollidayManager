package org.bluedevel.hollidaymanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Nightcrawler on 20.12.2016.
 */
@Entity
@Table(name="workday_definitions")
public class WorkdayDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private boolean dayToWork;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDayToWork() {
        return dayToWork;
    }

    public void setDayToWork(boolean dayToWork) {
        this.dayToWork = dayToWork;
    }

}
