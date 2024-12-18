package com.project.roombook.mapper;

import com.project.roombook.dto.BookingCreateDTO;
import com.project.roombook.dto.BookingResponseDTO;
import com.project.roombook.dto.RoomResponseDTO;
import com.project.roombook.dto.UserResponseDTO;
import com.project.roombook.entity.Booking;
import com.project.roombook.entity.Room;
import com.project.roombook.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {
    public static Booking toEntity(BookingCreateDTO bookingCreateDTO, Room room, User user) {
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setUser(user);
        booking.setTitle(bookingCreateDTO.getTitle());
        booking.setStartTime(bookingCreateDTO.getStartTime());
        booking.setEndTime(bookingCreateDTO.getEndTime());
        return booking;
    }

    public static BookingResponseDTO toResponseDTO(Booking booking) {
        RoomResponseDTO roomResponseDTO = RoomMapper.toResponseDTO(booking.getRoom());
        UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(booking.getUser());
        List<UserResponseDTO> participants = booking.getParticipants()
                .stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());

        return new BookingResponseDTO(
                booking.getId(),
                roomResponseDTO,
                userResponseDTO,
                booking.getTitle(),
                booking.getStartTime(),
                booking.getEndTime(),
                participants
        );
    }
}
