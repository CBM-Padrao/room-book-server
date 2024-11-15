package com.project.roombook.mapper;


import com.project.roombook.dto.RoomCreateDTO;
import com.project.roombook.dto.RoomResponseDTO;
import com.project.roombook.entity.Room;

public class RoomMapper {
    public static Room toEntity(RoomCreateDTO roomCreateDTO) {
        Room room = new Room();
        room.setName(roomCreateDTO.getName());
        return room;
    }

    public static RoomResponseDTO toResponseDTO(Room room) {
        return new RoomResponseDTO(
                room.getId(),
                room.getName(),
                room.getDisabledUntil(),
                room.getCreatedAt(),
                room.getUpdatedAt()
        );
    }
}
