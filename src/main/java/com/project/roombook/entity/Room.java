package com.project.roombook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date startSchedulingAt;
    private Date endSchedulingAt;
    private Date disabledUntil;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartSchedulingAt() {
        return startSchedulingAt;
    }

    public void setStartSchedulingAt(Date startSchedulingAt) {
        this.startSchedulingAt = startSchedulingAt;
    }

    public Date getEndSchedulingAt() {
        return endSchedulingAt;
    }

    public void setEndSchedulingAt(Date endSchedulingAt) {
        this.endSchedulingAt = endSchedulingAt;
    }

    public Date getDisabledUntil() {
        return disabledUntil;
    }

    public void setDisabledUntil(Date disabledUntil) {
        this.disabledUntil = disabledUntil;
    }
}
