package com.palagen.organizer.reminders.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reminders")
public class Rem implements Serializable{

    private int     id;
    private String  theme;
    private Date    date;
    private boolean done;
    private int     movable;

    public Rem(String theme) {
        this.theme   = theme;
    }

    public Rem() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "theme", nullable = false)
    public String getTheme() {
        return this.theme;
    }


    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "done", nullable = true)
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Column(name = "movable", nullable = true)
    public int getMovable() {
        return movable;
    }

    public void setMovable(int movable) {
        this.movable = movable;
    }

    @Column(name = "owner", nullable = false)
    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    private int     owner;




}
