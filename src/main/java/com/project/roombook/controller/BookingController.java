package com.project.roombook.controller;

import com.project.roombook.dto.*;
import com.project.roombook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@Validated @RequestBody BookingCreateDTO bookingCreateDTO) {
        BookingResponseDTO bookingResponseDTO = bookingService.createBooking(bookingCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingResponseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBooking(@Validated @RequestBody BookingUpdateDTO bookingUpdateDTO) {
        BookingResponseDTO bookingResponseDTO = bookingService.updateBooking(bookingUpdateDTO);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        BookingResponseDTO bookingResponseDTO = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingResponseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings(@RequestBody BookingFilterDTO bookingFilterDTO) {
        List<BookingResponseDTO> rooms = bookingService.getAllBookings(bookingFilterDTO);
        return ResponseEntity.ok(rooms);
    }
}
