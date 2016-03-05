package com.palagen.organizer.reminders.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reminders")
public class Rem implements Serializable{

    private long id;
    private String theme;

    public Rem() {
    }

    public Rem(String theme) {
        this.theme   = theme;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    public long getReminderId() {
        return this.id;
    }

    public void setReminderId(long userRoleId) {
        this.id = userRoleId;
    }

    @Column(name = "theme", nullable = false)
    public String getTheme() {
        return this.theme;
    }


    public void setTheme(String theme) {
        this.theme = theme;
    }

}
