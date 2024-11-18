package com.project.roombook.service;

import com.project.roombook.dto.BookingEmailDTO;
import com.project.roombook.entity.Booking;
import com.project.roombook.entity.User;
import com.project.roombook.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class BookingReminderService {

    private final BookingRepository bookingRepository;

    private final EmailService emailService;

    @Autowired
    public BookingReminderService(BookingRepository bookingRepository, EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.emailService = emailService;
    }
    @Scheduled(fixedRate = 60000) //1 min
    @Transactional
    public void sendReminders() {
        LocalDateTime oneHourFromNow = LocalDateTime.now(ZoneOffset.UTC).plusHours(1);

        List<Booking> upcomingBookings = bookingRepository.findByStartTimeBetween(oneHourFromNow.minusMinutes(1), oneHourFromNow);

        for (Booking booking : upcomingBookings) {
            emailService.sendReminderEmail(new BookingEmailDTO(
                    booking.getTitle(),
                    booking.getRoom().getName(),
                    booking.getStartTime(),
                    booking.getUser().getName(),
                    booking.getUser().getEmail()
            ));

            if (booking.getParticipants() != null) {
                for (User participant : booking.getParticipants()) {
                    emailService.sendReminderEmail(new BookingEmailDTO(
                            booking.getTitle(),
                            booking.getRoom().getName(),
                            booking.getStartTime(),
                            participant.getName(),
                            participant.getEmail()
                    ));
                }
            }
        }
    }


}

