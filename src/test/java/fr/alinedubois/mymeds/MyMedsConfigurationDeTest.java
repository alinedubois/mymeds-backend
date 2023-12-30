package fr.alinedubois.mymeds;

import fr.alinedubois.mymeds.notifications.MailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyMedsConfigurationDeTest {

    @Primary
    @Bean
    public MailSender mailSenderMyMeds() {
        return new MailSender() {

            private final List<String> sentEmails = new ArrayList<>();

            @Override
            public void send(SimpleMailMessage message) {
                this.sentEmails.add(message.getTo()[0]);
            }

            @Override
            public boolean hasSentEmailTo(String email) {
                var sentEmail = sentEmails.contains(email);
                sentEmails.clear();
                return sentEmail;
            }
        };
    }
}
