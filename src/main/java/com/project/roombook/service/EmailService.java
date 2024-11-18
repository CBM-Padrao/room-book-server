package com.project.roombook.service;

import com.project.roombook.dto.BookingEmailDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async("emailTaskExecutor")
    public void sendReminderEmail(BookingEmailDTO bookingEmailDTO) {
        String subject = "Lembrete de Reunião: " + bookingEmailDTO.getTitle();

        String text = "Olá, " + bookingEmailDTO.getUserName() +
                "\n\nLembrete que sua reunião na sala " + bookingEmailDTO.getRoomName() +
                " começará em 1 hora.\n" +
                "Horário: " +
                bookingEmailDTO.getStartTime()
                        .atZone(ZoneOffset.UTC)
                        .withZoneSameInstant(ZoneId.of("America/Sao_Paulo"))
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "\n\nAtenciosamente,\nEquipe de Reservas";

        sendEmail(bookingEmailDTO.getUserEmail(), subject, text);
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmail, "Equipe de reservas - Roombook");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail para: " + to);
            e.printStackTrace();
        }
    }
}
