package com.project.roombook.dto;

import java.util.Date;

public class RoomCreateDTO {
    private String name;
    private Date disabledUntil;

    public RoomCreateDTO() {
    }

    public RoomCreateDTO(String name, Date startSchedulingAt, Date endSchedulingAt, Date disabledUntil) {
        this.name = name;
        this.disabledUntil = disabledUntil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDisabledUntil() {
        return disabledUntil;
    }

    public void setDisabledUntil(Date disabledUntil) {
        this.disabledUntil = disabledUntil;
    }
}
