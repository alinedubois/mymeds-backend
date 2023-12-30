package fr.alinedubois.mymeds.notifications;

import org.springframework.mail.SimpleMailMessage;

public interface MailSender {
    void send(SimpleMailMessage message);
    boolean hasSentEmailTo(String email);
}
