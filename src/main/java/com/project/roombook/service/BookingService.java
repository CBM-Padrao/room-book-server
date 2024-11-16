package com.project.roombook.service;

import com.project.roombook.dto.BookingCreateDTO;
import com.project.roombook.dto.BookingFilterDTO;
import com.project.roombook.dto.BookingResponseDTO;
import com.project.roombook.dto.BookingUpdateDTO;
import com.project.roombook.entity.Booking;
import com.project.roombook.entity.Room;
import com.project.roombook.entity.User;
import com.project.roombook.exceptions.BookingAlreadyExistsException;
import com.project.roombook.mapper.BookingMapper;
import com.project.roombook.repository.BookingRepository;
import com.project.roombook.repository.RoomRepository;
import com.project.roombook.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Room room = roomRepository.findById(bookingCreateDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Sala informada para reserva não encontrada"));

        User user = userRepository.findById(bookingCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário informado para reserva não encontrado"));

        if (bookingCreateDTO.getStartTime() != null && bookingCreateDTO.getEndTime() != null &&
                bookingCreateDTO.getStartTime().isAfter(bookingCreateDTO.getEndTime())) {
            throw new IllegalArgumentException("A data de início não pode ser após a data de término.");
        }

        if (bookingRepository.existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                bookingCreateDTO.getRoomId(), bookingCreateDTO.getEndTime(), bookingCreateDTO.getStartTime())) {
            throw new BookingAlreadyExistsException("Já existe uma reserva para a sala durante este período");
        }

        Booking booking = BookingMapper.toEntity(bookingCreateDTO, room, user);

        if (bookingCreateDTO.getParticipantIds() != null && !bookingCreateDTO.getParticipantIds().isEmpty()) {
            List<User> participants = userRepository.findAllById(bookingCreateDTO.getParticipantIds());
            booking.setParticipants(participants);
        }

        bookingRepository.save(booking);
        return BookingMapper.toResponseDTO(booking);
    }

    @Transactional
    public BookingResponseDTO updateBooking(BookingUpdateDTO bookingUpdateDTO) {
        Booking booking = bookingRepository.findById(bookingUpdateDTO.getId())
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        if (bookingUpdateDTO.getRoomId() != null) {
            Room room = roomRepository.findById(bookingUpdateDTO.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Sala informada para reserva não encontrada"));
            booking.setRoom(room);
        }

        if (bookingUpdateDTO.getStartTime() != null) {
            booking.setStartTime(bookingUpdateDTO.getStartTime());
        }

        if (bookingUpdateDTO.getEndTime() != null) {
            booking.setEndTime(bookingUpdateDTO.getEndTime());
        }

        if (booking.getStartTime() != null && booking.getEndTime() != null &&
                booking.getStartTime().isAfter(booking.getEndTime())) {
            throw new IllegalArgumentException("A data de início não pode ser após a data de término.");
        }

        if (bookingRepository.existsByRoomIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqualAndIdNot(
                booking.getRoom().getId(), booking.getEndTime(), booking.getStartTime(), booking.getId())) {
            throw new BookingAlreadyExistsException("Já existe uma reserva para a sala durante este período");
        }

        bookingRepository.save(booking);
        return BookingMapper.toResponseDTO(booking);
    }

    @Transactional
    public BookingResponseDTO deleteBooking(Long id) {
        Booking booking = bookingRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        booking.setDeleted(true);
        bookingRepository.save(booking);
        return BookingMapper.toResponseDTO(booking);
    }

    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        return BookingMapper.toResponseDTO(booking);
    }

    public List<BookingResponseDTO> getAllBookings(BookingFilterDTO bookingFilterDTO) {
        Specification<Booking> spec = Specification.where(null);

        if (bookingFilterDTO != null) {
            if (bookingFilterDTO.roomId() != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("room").get("id"), bookingFilterDTO.roomId()));
            }

            if (bookingFilterDTO.userId() != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get("user").get("id"), bookingFilterDTO.userId()));
            }

            if (bookingFilterDTO.startTime() != null && bookingFilterDTO.endTime() != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.and(
                                criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), bookingFilterDTO.startTime()),
                                criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), bookingFilterDTO.endTime())
                        ));
            } else if (bookingFilterDTO.startTime() != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), bookingFilterDTO.startTime()));
            } else if (bookingFilterDTO.endTime() != null) {
                spec = spec.and((root, query, criteriaBuilder) ->
                        criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), bookingFilterDTO.endTime()));
            }
        }

        List<Booking> bookings = bookingRepository.findAll(spec);
        return bookings.stream().map(BookingMapper::toResponseDTO).collect(Collectors.toList());
    }
}
