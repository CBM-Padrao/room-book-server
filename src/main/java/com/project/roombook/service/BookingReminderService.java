package com.project.roombook.service;

import com.project.roombook.entity.Booking;
import com.project.roombook.entity.User;
import com.project.roombook.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        LocalDateTime oneHourFromNow = LocalDateTime.now().plusHours(1);

        List<Booking> upcomingBookings = bookingRepository.findByStartTimeBetween(oneHourFromNow.minusMinutes(1), oneHourFromNow);

        for (Booking booking : upcomingBookings) {
            String creatorEmail = booking.getUser().getEmail();
            sendReminderEmail(creatorEmail, booking);

            if (booking.getParticipants() != null) {
                for (User participant : booking.getParticipants()) {
                    sendReminderEmail(participant.getEmail(), booking);
                }
            }
        }
    }

    private void sendReminderEmail(String email, Booking booking) {
        String subject = "Lembrete de Reunião: " + booking.getRoom().getName();
        String text = "Olá,\n\nLembrete que sua reunião na sala " + booking.getRoom().getName() +
                " começará em 1 hora.\n" +
                "Horário: " + booking.getStartTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "\n\nAtenciosamente,\nEquipe de Reservas";

        emailService.sendEmail(email, subject, text);
    }
}

