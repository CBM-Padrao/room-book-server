package com.project.roombook.service;

import com.project.roombook.dto.RoomCreateDTO;
import com.project.roombook.dto.RoomResponseDTO;
import com.project.roombook.dto.RoomUpdateDTO;
import com.project.roombook.entity.Room;
import com.project.roombook.mapper.RoomMapper;
import com.project.roombook.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public RoomResponseDTO createRoom(RoomCreateDTO roomCreateDTO) {
        Room room = RoomMapper.toEntity(roomCreateDTO);
        roomRepository.save(room);
        return RoomMapper.toResponseDTO(room);
    }

    @Transactional
    public RoomResponseDTO updateRoom(RoomUpdateDTO roomUpdateDTO) {
        Room room = roomRepository.findById(roomUpdateDTO.getId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        if (roomUpdateDTO.getName() != null) {
            room.setName(roomUpdateDTO.getName());
        }

        Room updatedRoom = roomRepository.save(room);
        return RoomMapper.toResponseDTO(updatedRoom);
    }

    @Transactional
    public RoomResponseDTO deleteRoom(Long id) {
        Room room = roomRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        room.setDeleted(true);
        roomRepository.save(room);
        return RoomMapper.toResponseDTO(room);
    }

    public RoomResponseDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        return RoomMapper.toResponseDTO(room);
    }

    public List<RoomResponseDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findByIsDeletedFalse();
        return rooms.stream()
                .map(RoomMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
