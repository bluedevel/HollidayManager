package org.bluedevel.hollidaymanager.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

/**
 * Created by Nightcrawler on 02.01.2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class AbstractWorkdayDefinition {

    //@GeneratedValue(strategy = GenerationType.AUTO)
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

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
}
