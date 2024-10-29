package com.project.roombook.model;

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
}
