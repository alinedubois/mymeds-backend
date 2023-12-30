package fr.alinedubois.mymeds.notifications;

import fr.alinedubois.mymeds.pharmacie.service.BoiteDeMedicament;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicamentPerimeNotification {
    private final MailSender emailSender;
    private final String from;

    public MedicamentPerimeNotification(MailSender emailSender, @Value("${spring.mail.username}") String from) {
        this.emailSender = emailSender;
        this.from = from;
    }

    void envoiDeNotification(String adresseEmail, List<BoiteDeMedicament> boitesDeMedicaments) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(adresseEmail);
        message.setSubject("Alerte de médicaments périmés ou bientôt périmés");
        message.setText("Cher utilisateur, Chère utilisatrice,\n Après vérification de votre pharmacie, nous avons constaté que les médicaments suivants sont périmés ou bientôt périmés. \n Nous vous invitons à faire le tri dans vos médicaments.%s \n \n A bientôt \n L'équipe de MyMeds".formatted(
                boitesDeMedicaments.stream()
                        .map(boiteDeMedicament -> boiteDeMedicament.texteDePeremption())
                        .collect(Collectors.joining(",\n"))
        ));
        emailSender.send(message);
    }
}
