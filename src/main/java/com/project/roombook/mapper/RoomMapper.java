package com.project.roombook.mapper;


import com.project.roombook.dto.RoomDTO;
import com.project.roombook.entity.Room;

public class RoomMapper {
    public static Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setStartSchedulingAt(roomDTO.getStartSchedulingAt());
        room.setEndSchedulingAt(roomDTO.getEndSchedulingAt());
        room.setDisabledUntil(roomDTO.getDisabledUntil());
        return room;
    }

    public static RoomDTO toDTO(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getName(),
                room.getStartSchedulingAt(),
                room.getEndSchedulingAt(),
                room.getDisabledUntil()
        );
    }
}
