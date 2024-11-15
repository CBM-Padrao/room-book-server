package com.project.roombook.controller;

import com.project.roombook.dto.RoomCreateDTO;
import com.project.roombook.dto.RoomResponseDTO;
import com.project.roombook.dto.RoomUpdateDTO;
import com.project.roombook.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@Validated @RequestBody RoomCreateDTO roomCreateDTO) {
        RoomResponseDTO roomResponseDTO = roomService.createRoom(roomCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomResponseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRoom(@Validated @RequestBody RoomUpdateDTO roomUpdateDTO) {
        RoomResponseDTO roomResponseDTO = roomService.updateRoom(roomUpdateDTO);
        return ResponseEntity.ok(roomResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        RoomResponseDTO roomResponseDTO = roomService.getRoomById(id);
        return ResponseEntity.ok(roomResponseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        List<RoomResponseDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}
