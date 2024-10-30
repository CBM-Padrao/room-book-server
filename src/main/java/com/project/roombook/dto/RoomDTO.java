package com.project.roombook.dto;

import java.util.Date;

public class RoomDTO {
    private Long id;
    private String name;
    private Date startSchedulingAt;
    private Date endSchedulingAt;
    private Date disabledUntil;

    public RoomDTO() {
    }

    public RoomDTO(Long id, String name, Date startSchedulingAt, Date endSchedulingAt, Date disabledUntil) {
        this.id = id;
        this.name = name;
        this.startSchedulingAt = startSchedulingAt;
        this.endSchedulingAt = endSchedulingAt;
        this.disabledUntil = disabledUntil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
