package fr.alinedubois.mymeds.notifications;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderJavaMail implements MailSender {

    private final JavaMailSender javaMailSender;

    public MailSenderJavaMail(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(SimpleMailMessage message) {
        this.javaMailSender.send(message);
    }

    @Override
    public boolean hasSentEmailTo(String email) {
        throw new RuntimeException(new IllegalAccessException());
    }
}
