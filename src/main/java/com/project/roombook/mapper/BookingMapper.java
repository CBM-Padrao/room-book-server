package com.project.roombook.mapper;

import com.project.roombook.dto.BookingCreateDTO;
import com.project.roombook.dto.BookingResponseDTO;
import com.project.roombook.dto.RoomResponseDTO;
import com.project.roombook.dto.UserResponseDTO;
import com.project.roombook.entity.Booking;
import com.project.roombook.entity.Room;
import com.project.roombook.entity.User;

public class BookingMapper {
    public static Booking toEntity(BookingCreateDTO bookingCreateDTO) {
        Booking booking = new Booking();
        if (bookingCreateDTO.getRoom() != null) {
            Room room = new Room();
            room.setId(bookingCreateDTO.getRoom().getId());
            booking.setRoom(room);
        }
        if (bookingCreateDTO.getUser() != null) {
            User user = new User();
            user.setId(bookingCreateDTO.getUser().getId());
            booking.setUser(user);
        }
        booking.setStartTime(bookingCreateDTO.getStartTime());
        booking.setEndTime(bookingCreateDTO.getEndTime());
        return booking;
    }

    public static BookingResponseDTO toResponseDTO(Booking booking) {
        RoomResponseDTO roomResponseDTO = RoomMapper.toResponseDTO(booking.getRoom());
        UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(booking.getUser());

        return new BookingResponseDTO(
                roomResponseDTO,
                userResponseDTO,
                booking.getStartTime(),
                booking.getEndTime()
        );
    }
}
