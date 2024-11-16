package com.project.roombook.service;

import com.project.roombook.dto.BookingCreateDTO;
import com.project.roombook.dto.BookingResponseDTO;
import com.project.roombook.entity.Booking;
import com.project.roombook.exceptions.BookingAlreadyExistsException;
import com.project.roombook.mapper.BookingMapper;
import com.project.roombook.repository.BookingRepository;
import com.project.roombook.repository.RoomRepository;
import com.project.roombook.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BookingResponseDTO createBooking(BookingCreateDTO bookingCreateDTO) {
        if (!roomRepository.existsById(bookingCreateDTO.getRoom().getId())) {
            throw new RuntimeException("A sala informada para reserva não existe");
        }

        if (!userRepository.existsById(bookingCreateDTO.getUser().getId())) {
            throw new RuntimeException("O usuário informada para reserva da sala não existe");
        }

        if (bookingRepository.existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                bookingCreateDTO.getRoom().getId(), bookingCreateDTO.getEndTime(), bookingCreateDTO.getStartTime())) {
            throw new BookingAlreadyExistsException("Já existe uma reserva para a sala durante este período");
        }

        Booking booking = BookingMapper.toEntity(bookingCreateDTO);
        bookingRepository.save(booking);
        return BookingMapper.toResponseDTO(booking);
    }
}
