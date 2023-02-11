package fr.alinedubois.mymeds;

import com.dumbster.smtp.SmtpMessage;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

public class EmailAsserter extends AbstractAssert<EmailAsserter, String> {
    private SmtpMessage messageRecu;

    private EmailAsserter(String destinataire) {
        super(destinataire, EmailAsserter.class);
    }

    public static EmailAsserter assertThatAnEmailTo (String destinataire) {
        return new EmailAsserter(destinataire);
    }

    public EmailAsserter wasNotSent() {
        List<SmtpMessage> mailsRecus = ServeurMailExtension.serveurDeMails.getReceivedEmails();
        if (mailsRecus.stream().anyMatch(message -> message.getHeaderValue("To").contains(actual))) {
            failWithMessage("Un email a été reçu par " + actual);
        }
        return this;
    }

    public EmailAsserter wasSent() {
        List<SmtpMessage> mailsRecus = ServeurMailExtension.serveurDeMails.getReceivedEmails();
        if (mailsRecus.stream().noneMatch(message -> message.getHeaderValue("To").contains(actual))) {
            failWithMessage("Aucun email n'a été reçu par " + actual);
        } else {
            this.messageRecu = mailsRecus.stream().filter(message -> message.getHeaderValue("To").contains(actual)).findFirst().get();
        }
        return this;
    }

    public EmailAsserter withSubject(String objetDuMail) {
        if (this.messageRecu != null) {
        }
        return this;
    }
}
