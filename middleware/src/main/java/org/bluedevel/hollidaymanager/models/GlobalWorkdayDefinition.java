package org.bluedevel.hollidaymanager.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Nightcrawler on 02.01.2017.
 */
@Entity
@Table(name = "global_workday_definitions")
public class GlobalWorkdayDefinition implements WorkdayDefinition {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;

    @NotNull
    private boolean monday;

    @NotNull
    private boolean tuesday;

    @NotNull
    private boolean wednesday;

    @NotNull
    private boolean thursday;

    @NotNull
    private boolean friday;

    @NotNull
    private boolean saturday;

    @NotNull
    private boolean sunday;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isMonday() {
        return monday;
    }

    @Override
    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    @Override
    public boolean isTuesday() {
        return tuesday;
    }

    @Override
    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    @Override
    public boolean isWednesday() {
        return wednesday;
    }

    @Override
    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    @Override
    public boolean isThursday() {
        return thursday;
    }

    @Override
    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    @Override
    public boolean isFriday() {
        return friday;
    }

    @Override
    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    @Override
    public boolean isSaturday() {
        return saturday;
    }

    @Override
    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    @Override
    public boolean isSunday() {
        return sunday;
    }

    @Override
    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

}
