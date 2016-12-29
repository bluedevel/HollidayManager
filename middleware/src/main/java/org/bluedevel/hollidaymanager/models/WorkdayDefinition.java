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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkdayDefinition that = (WorkdayDefinition) o;

        if (id != that.id) return false;
        if (dayToWork != that.dayToWork) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (dayToWork ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WorkdayDefinition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dayToWork=" + dayToWork +
                '}';
    }
}
