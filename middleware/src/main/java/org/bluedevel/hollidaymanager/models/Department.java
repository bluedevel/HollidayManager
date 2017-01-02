package org.bluedevel.hollidaymanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Nightcrawler on 20.12.2016.
 */
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
